package com.capstone.tvchat.api.bbs.repository;

import com.capstone.tvchat.api.bbs.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAll();
}
