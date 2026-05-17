// com/wassalni/controller/MessageController.java
package com.wassalni.controller;

import com.wassalni.model.Message;
import com.wassalni.model.Personnel;
import com.wassalni.model.Gouvernorat;
import com.wassalni.repository.MessageRepository;
import com.wassalni.repository.PersonnelRepository;
import com.wassalni.repository.GouvernoratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired private MessageRepository    messageRepository;
    @Autowired private PersonnelRepository  personnelRepository;
    @Autowired private GouvernoratRepository gouvernoratRepository;

    // -------------------------------------------------------
    // GET /api/messages/conversation?user1=1&user2=2
    // -------------------------------------------------------
    @GetMapping("/conversation")
    public ResponseEntity<List<Map<String, Object>>> getConversation(
            @RequestParam Long user1, @RequestParam Long user2) {

        List<Message> messages = messageRepository.findConversation(user1, user2);
        List<Map<String, Object>> result = new ArrayList<>();

        Map<Long, String> noms = new HashMap<>();
        personnelRepository.findById(user1).ifPresent(p ->
            noms.put(user1, p.getPrenompers() + " " + p.getNompers()));
        personnelRepository.findById(user2).ifPresent(p ->
            noms.put(user2, p.getPrenompers() + " " + p.getNompers()));

        for (Message m : messages) {
            Map<String, Object> map = new HashMap<>();
            map.put("idmsg",         m.getIdmsg());
            map.put("expediteur",    m.getExpediteur());
            map.put("destinataire",  m.getDestinataire());
            map.put("nomExpediteur", noms.getOrDefault(m.getExpediteur(), "?"));
            map.put("contenu",       m.getContenu());
            map.put("heureenvoi",    m.getHeureenvoi() != null ?
                                     m.getHeureenvoi().toString() : "");
            map.put("typeMsg",       m.getTypeMsg());
            map.put("nocde",         m.getNocde());
            map.put("lu",            m.getLu());
            result.add(map);
        }
        return ResponseEntity.ok(result);
    }

    // -------------------------------------------------------
    // GET /api/messages/nonlus?userId=1
    // -------------------------------------------------------
    @GetMapping("/nonlus")
    public ResponseEntity<List<Map<String, Object>>> getNonLus(@RequestParam Long userId) {

        List<Message> messages =
            messageRepository.findByDestinataireAndLuOrderByHeureenvoi(userId, 0);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Message m : messages) {
            Map<String, Object> map = new HashMap<>();
            map.put("idmsg",       m.getIdmsg());
            map.put("expediteur",  m.getExpediteur());
            map.put("contenu",     m.getContenu());
            map.put("typeMsg",     m.getTypeMsg());
            map.put("heureenvoi",  m.getHeureenvoi() != null ?
                                   m.getHeureenvoi().toString() : "");
            map.put("nocde",       m.getNocde());

            personnelRepository.findById(m.getExpediteur()).ifPresent(p ->
                map.put("nomExpediteur", p.getPrenompers() + " " + p.getNompers()));

            result.add(map);
        }
        return ResponseEntity.ok(result);
    }

    // -------------------------------------------------------
    // POST /api/messages/envoyer
    // -------------------------------------------------------
    @PostMapping("/envoyer")
    public ResponseEntity<Map<String, Object>> envoyerMessage(
            @RequestBody Map<String, Object> body) {
        try {
            Message msg = new Message();
            msg.setIdmsg(System.currentTimeMillis());
            msg.setExpediteur(Long.parseLong(body.get("expediteur").toString()));
            msg.setDestinataire(Long.parseLong(body.get("destinataire").toString()));
            msg.setContenu((String) body.get("contenu"));
            msg.setTypeMsg(body.getOrDefault("typeMsg", "Normal").toString());
            msg.setHeureenvoi(LocalDateTime.now());
            msg.setLu(0);
            if (body.get("nocde") != null)
                msg.setNocde(Long.parseLong(body.get("nocde").toString()));

            messageRepository.save(msg);

            Map<String, Object> response = new HashMap<>();
            response.put("success",    true);
            response.put("idmsg",      msg.getIdmsg());
            response.put("contenu",    msg.getContenu());
            response.put("typeMsg",    msg.getTypeMsg());
            response.put("heureenvoi", msg.getHeureenvoi().toString());

            personnelRepository.findById(msg.getExpediteur()).ifPresent(p ->
                response.put("nomExpediteur",
                    p.getPrenompers() + " " + p.getNompers()));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> err = new HashMap<>();
            err.put("success", false);
            err.put("erreur",  e.getMessage());
            return ResponseEntity.status(500).body(err);
        }
    }

    // -------------------------------------------------------
    // PUT /api/messages/{id}/lu
    // -------------------------------------------------------
    @PutMapping("/{id}/lu")
    public ResponseEntity<Void> marquerLu(@PathVariable Long id) {
        messageRepository.findById(id).ifPresent(msg -> {
            msg.setLu(1);
            messageRepository.save(msg);
        });
        return ResponseEntity.ok().build();
    }
    

    // -------------------------------------------------------
    // GET /api/messages/livreurs?controleurId=1
    // Liste des livreurs AVEC leur gouvernorat
    // -------------------------------------------------------
    @GetMapping("/livreurs")
    public ResponseEntity<List<Map<String, Object>>> getLivreursDisponibles(
            @RequestParam Long controleurId) {

        List<Personnel> livreurs = personnelRepository.findByCodeposte("LIV");
        List<Map<String, Object>> result = new ArrayList<>();

        for (Personnel p : livreurs) {
            Map<String, Object> map = new HashMap<>();
            map.put("idpers",  p.getIdpers());
            map.put("nom",     p.getNompers());
            map.put("prenom",  p.getPrenompers());
            map.put("tel",     p.getTelpers());
            map.put("idgouv",  p.getIdgouv());

            // Ajouter le nom du gouvernorat
            if (p.getIdgouv() != null) {
                gouvernoratRepository.findById(p.getIdgouv()).ifPresent(g ->
                    map.put("gouvernorat", g.getNomgouv()));
            } else {
                map.put("gouvernorat", "Non assigné");
            }

            result.add(map);
        }
        return ResponseEntity.ok(result);
    }
	 // -------------------------------------------------------
	 // PUT /api/messages/lireTout?userId=1&expediteurId=2
	 // Marque tous les messages d'une conversation comme lus
	 // -------------------------------------------------------
	 @PutMapping("/lireTout")
	 public ResponseEntity<Void> marquerConversationLue(
	         @RequestParam Long userId, 
	         @RequestParam Long expediteurId) {
	     
	     // 1. On récupère tous les messages non lus (lu=0) destinés à l'utilisateur connecté
	     List<Message> messagesNonLus = 
	         messageRepository.findByDestinataireAndLuOrderByHeureenvoi(userId, 0);
	     
	     // 2. On filtre ceux qui proviennent de l'interlocuteur spécifique et on les marque comme lus
	     for (Message m : messagesNonLus) {
	         if (m.getExpediteur().equals(expediteurId)) {
	             m.setLu(1);
	             messageRepository.save(m);
	         }
	     }
	     
	     return ResponseEntity.ok().build();
	 }
}