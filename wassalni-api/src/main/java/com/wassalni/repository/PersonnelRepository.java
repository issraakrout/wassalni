// com/wassalni/repository/PersonnelRepository.java
package com.wassalni.repository;

import com.wassalni.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

    // Login
    Optional<Personnel> findByLoginAndMotP(String login, String motP);

    // Tous les livreurs
    List<Personnel> findByCodeposte(String codeposte);

    // Livreurs d'un gouvernorat
    List<Personnel> findByCodeposteAndIdgouv(String codeposte, Long idgouv);
}