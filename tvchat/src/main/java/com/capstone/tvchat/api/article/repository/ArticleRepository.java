package com.capstone.tvchat.api.article.repository;

import com.capstone.tvchat.api.article.domain.entity.Article;
import com.capstone.tvchat.api.article.repository.custom.ArticleCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
}
