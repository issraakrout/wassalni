package com.wassalni.repository;

import com.wassalni.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Articles, String> {
}