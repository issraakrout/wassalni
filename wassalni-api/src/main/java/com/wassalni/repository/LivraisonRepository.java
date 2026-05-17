// com/wassalni/repository/LivraisonRepository.java
package com.wassalni.repository;

import com.wassalni.model.LivraisonCom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface LivraisonRepository extends JpaRepository<LivraisonCom, Long> {

    // Livraisons du jour pour un livreur
    List<LivraisonCom> findByLivreurAndDateliv(Long livreur, LocalDate dateliv);

    // Livraisons entre deux dates
    List<LivraisonCom> findByDatelivBetween(LocalDate debut, LocalDate fin);

    // Livraisons supervisées par un contrôleur spécifique
    List<LivraisonCom> findByControleur(Long controleur);
    
    List<LivraisonCom> findByLivreur(Long livreurId);


    // Livraisons d'un contrôleur entre deux dates
    List<LivraisonCom> findByControleurAndDatelivBetween(Long controleur,
                                                          LocalDate debut,
                                                          LocalDate fin);
}