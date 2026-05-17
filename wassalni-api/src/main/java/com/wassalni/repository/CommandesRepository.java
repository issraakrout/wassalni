package com.wassalni.repository;

import com.wassalni.model.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandesRepository extends JpaRepository<Commandes, Long> {
}