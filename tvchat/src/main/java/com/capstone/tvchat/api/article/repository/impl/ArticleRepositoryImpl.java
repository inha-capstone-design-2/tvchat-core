package com.capstone.tvchat.api.article.repository.impl;

import com.capstone.tvchat.api.article.domain.entity.Article;
import com.capstone.tvchat.api.article.repository.ArticleRepository;
import com.capstone.tvchat.common.support.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;


public class ArticleRepositoryImpl extends Querydsl4RepositorySupport implements ArticleRepository {
    public ArticleRepositoryImpl() {
        super(Article.class);
    }
}
