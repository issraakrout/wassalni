package com.wassalni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wassalni.model.Articles;
import com.wassalni.model.Clients;
import com.wassalni.model.Commandes;
import com.wassalni.model.LivraisonCom;
import com.wassalni.model.Personnel;
import com.wassalni.repository.ClientsRepository;
import com.wassalni.repository.CommandesRepository;
import com.wassalni.repository.LigCdesRepository;
import com.wassalni.repository.LivraisonRepository;
import com.wassalni.repository.PersonnelRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.wassalni.repository.*;
import org.springframework.transaction.annotation.Transactional;


@RestController
@RequestMapping("/api/livraisons")
@CrossOrigin(origins = "*")
public class LivraisonController {

    @Autowired
    private LivraisonRepository livraisonRepository;
    @Autowired private CommandesRepository commandesRepository;
    @Autowired private ClientsRepository clientsRepository;
    @Autowired private PersonnelRepository personnelRepository;
    @Autowired private LigCdesRepository ligCdesRepository;
    @Autowired private ArticlesRepository articlesRepository;
    // -------------------------------------------------------
    // GET /api/livraisons
    // Toutes les livraisons (pour le contrôleur)
    // -------------------------------------------------------
    @GetMapping
    public List<LivraisonCom> getAllLivraisons() {
        return livraisonRepository.findAll();
    }

    // -------------------------------------------------------
    // GET /api/livraisons/today?livreurId=2
    // Livraisons du jour pour UN livreur
    // -------------------------------------------------------
    @GetMapping("/today")
    public List<LivraisonCom> getLivraisonsToday(@RequestParam Long livreurId) {
        LocalDate today = LocalDate.now();
        return livraisonRepository.findByLivreurAndDateliv(livreurId, today);
    }

    // -------------------------------------------------------
    // GET /api/livraisons/periode?debut=2026-04-01&fin=2026-04-02
    // Livraisons entre deux dates (pour le contrôleur)
    // -------------------------------------------------------
    @GetMapping("/periode")
    public List<LivraisonCom> getLivraisonsPeriode(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return livraisonRepository.findByDatelivBetween(debut, fin);
    }

    // -------------------------------------------------------
    // GET /api/livraisons/{id}
    // Détail d'une livraison
    // -------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<LivraisonCom> getLivraison(@PathVariable Long id) {
        Optional<LivraisonCom> liv = livraisonRepository.findById(id);
        return liv.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------
    // PUT /api/livraisons/{id}/etat
    // Modifier l'état d'une livraison (livreur)
    // Body JSON : { "etatliv": "Livrée", "remarque": "..." }
    // -------------------------------------------------------
    
    
 // Ajoutez cette méthode dans LivraisonController.java (côté Spring Boot)
 // Elle retourne toutes les infos d'une livraison en une seule requête

 // -------------------------------------------------------
 // GET /api/livraisons/{id}/detail
 // Détail complet : livraison + client + livreur + montant
 // -------------------------------------------------------
	 @GetMapping("/{id}/detail")
	 public ResponseEntity<Map<String, Object>> getDetailComplet(@PathVariable Long id) {
	
	     // Récupérer la livraison
	     Optional<LivraisonCom> livOpt = livraisonRepository.findById(id);
	     if (livOpt.isEmpty()) return ResponseEntity.notFound().build();
	     LivraisonCom liv = livOpt.get();
	
	     // Récupérer la commande
	     Optional<Commandes> cdeOpt = commandesRepository.findById(id);
	
	     Map<String, Object> result = new HashMap<>();
	
	     // Infos livraison
	     result.put("nocde",        liv.getNocde());
	     result.put("dateLivraison", liv.getDateliv().toString());
	     result.put("modepay",      liv.getModepay());
	     result.put("etatliv",      liv.getEtatliv());
	
	     // Infos commande
	     if (cdeOpt.isPresent()) {
	         Commandes cde = cdeOpt.get();
	         result.put("dateCommande", cde.getDatecde().toString());
	
	         // Infos client
	         Optional<Clients> cltOpt = clientsRepository.findById(cde.getNoclt());
	         if (cltOpt.isPresent()) {
	             Clients clt = cltOpt.get();
	             result.put("nomClient",     clt.getNomclt());
	             result.put("prenomClient",  clt.getPrenomclt());
	             result.put("telClient",     clt.getTelclt());
	             result.put("adresseClient", clt.getAdrclt());
	             result.put("villeClient",   clt.getVilleclt());
	         }
	
	         // Montant total de la commande
	         double montant = ligCdesRepository.findByNocde(id)
	             .stream()
	             .mapToDouble(l -> {
	                 Optional<Articles> artOpt = articlesRepository.findById(l.getRefart());
	                 return artOpt.map(a -> a.getPrixV() * l.getQtecde()).orElse(0.0);
	             }).sum();
	         result.put("montantTotal", montant);
	     }
	
	     // Infos livreur
	     Optional<Personnel> livPersonnelOpt = personnelRepository.findById(liv.getLivreur());
	     if (livPersonnelOpt.isPresent()) {
	         Personnel p = livPersonnelOpt.get();
	         result.put("nomLivreur",    p.getNompers());
	         result.put("prenomLivreur", p.getPrenompers());
	         result.put("telLivreur",    p.getTelpers());
	     }
	
	     return ResponseEntity.ok(result);
	 }
	 @GetMapping("/filtre")
	 public List<LivraisonCom> filtrerParDate(
	         @RequestParam String dateDebut,
	         @RequestParam String dateFin) {

	     LocalDate debut = LocalDate.parse(dateDebut);
	     LocalDate fin   = LocalDate.parse(dateFin);

	     return livraisonRepository.findByDatelivBetween(debut, fin);
	 }
	 
	// Ajoutez cette méthode dans LivraisonController.java (Spring Boot)
	// GET /api/livraisons/livreur/{id}/details
	// Retourne toutes les infos pour la sync SQLite du livreur

	@GetMapping("/livreur/{id}/details")
	public ResponseEntity<List<Map<String, Object>>> getDetailsLivreur(
	        @PathVariable Long id) {

	    // Livraisons du jour pour ce livreur
	    LocalDate today = LocalDate.now();
	    List<LivraisonCom> livraisons =
	        livraisonRepository.findByLivreurAndDateliv(id, today);

	    List<Map<String, Object>> result = new ArrayList<>();

	    for (LivraisonCom liv : livraisons) {
	        Map<String, Object> item = new HashMap<>();

	        // Infos livraison
	        item.put("nocde",        liv.getNocde());
	        item.put("dateLivraison", liv.getDateliv().toString());
	        item.put("modepay",      liv.getModepay());
	        item.put("etatliv",      liv.getEtatliv());

	        // Infos commande + client
	        commandesRepository.findById(liv.getNocde()).ifPresent(cde -> {
	            item.put("dateCommande", cde.getDatecde().toString());

	            clientsRepository.findById(cde.getNoclt()).ifPresent(clt -> {
	                item.put("nomClient",     clt.getNomclt());
	                item.put("prenomClient",  clt.getPrenomclt());
	                item.put("telClient",     clt.getTelclt());
	                item.put("adresseClient", clt.getAdrclt());
	                item.put("villeClient",   clt.getVilleclt());
	            });

	            // Montant total
	            double montant = ligCdesRepository.findByNocde(liv.getNocde())
	                .stream()
	                .mapToDouble(l -> {
	                    return articlesRepository.findById(l.getRefart())
	                        .map(a -> a.getPrixV() * l.getQtecde())
	                        .orElse(0.0);
	                }).sum();
	            item.put("montantTotal", montant);
	            item.put("nbArticles", ligCdesRepository.findByNocde(liv.getNocde()).size());
	        });

	        result.add(item);
	    }

	    return ResponseEntity.ok(result);
	}
	@Transactional  // ✅ Force le commit avant de retourner
	@PutMapping("/{id}/etat")
	public ResponseEntity<LivraisonCom> updateEtat(
	    @PathVariable Long id,
	    @RequestBody Map<String, String> body) {

	    Optional<LivraisonCom> optional = livraisonRepository.findById(id);
	    if (optional.isEmpty()) return ResponseEntity.notFound().build();

	    LivraisonCom liv = optional.get();
	    liv.setEtatliv(body.get("etatliv"));
	    livraisonRepository.saveAndFlush(liv); // ✅ saveAndFlush au lieu de save
	    return ResponseEntity.ok(liv);
	}
	
	// GET /api/livraisons/stats
	// Retourne toutes les livraisons avec montant pour les statistiques
	@GetMapping("/stats")
	public ResponseEntity<List<Map<String, Object>>> getAllLivraisonsAvecMontant() {
	    List<LivraisonCom> livraisons = livraisonRepository.findAll();
	    List<Map<String, Object>> result = new ArrayList<>();

	    for (LivraisonCom liv : livraisons) {
	        Map<String, Object> item = new HashMap<>();
	        item.put("nocde",   liv.getNocde());
	        item.put("dateliv", liv.getDateliv().toString());
	        item.put("etatliv", liv.getEtatliv());
	        item.put("livreur", liv.getLivreur());

	        // Nom du livreur
	        personnelRepository.findById(liv.getLivreur()).ifPresent(p -> {
	            item.put("nomLivreur",    p.getNompers());
	            item.put("prenomLivreur", p.getPrenompers());
	        });

	        // Montant total
	        double montant = ligCdesRepository.findByNocde(liv.getNocde())
	            .stream()
	            .mapToDouble(l -> articlesRepository.findById(l.getRefart())
	                .map(a -> a.getPrixV() * l.getQtecde()).orElse(0.0))
	            .sum();
	        item.put("montantTotal", montant);

	        result.add(item);
	    }
	    return ResponseEntity.ok(result);
	}
}
