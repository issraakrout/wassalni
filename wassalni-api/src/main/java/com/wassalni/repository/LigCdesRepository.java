package com.wassalni.repository;

import com.wassalni.model.LigCdes;
import com.wassalni.model.LigCdesId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LigCdesRepository extends JpaRepository<LigCdes, LigCdesId> {
    List<LigCdes> findByNocde(Long nocde);
}