package com.capstone.tvchat.api.article.repository;

import com.capstone.tvchat.api.article.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
