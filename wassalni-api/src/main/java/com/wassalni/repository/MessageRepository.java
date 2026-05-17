// com/wassalni/repository/MessageRepository.java
package com.wassalni.repository;

import com.wassalni.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Conversation entre 2 personnes (dans les 2 sens), triée par heure
    @Query("SELECT m FROM Message m WHERE " +
           "(m.expediteur = :u1 AND m.destinataire = :u2) OR " +
           "(m.expediteur = :u2 AND m.destinataire = :u1) " +
           "ORDER BY m.heureenvoi ASC")
    List<Message> findConversation(@Param("u1") Long u1, @Param("u2") Long u2);

    // Messages non lus pour un utilisateur
    List<Message> findByDestinataireAndLuOrderByHeureenvoi(Long destinataire, Integer lu);

    // Tous les messages reçus
    List<Message> findByDestinataireOrderByHeureenvoi(Long destinataire);
}