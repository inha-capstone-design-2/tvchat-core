package com.capstone.tvchat.api.bbs.repository.impl;

import com.capstone.tvchat.api.bbs.domain.entity.Article;
import com.capstone.tvchat.api.bbs.repository.ArticleRepository;
import com.capstone.tvchat.api.bbs.repository.custom.ArticleCustomRepository;
import com.capstone.tvchat.common.support.Querydsl4RepositorySupport;

public class ArticleRepositoryImpl extends Querydsl4RepositorySupport implements ArticleCustomRepository {
    public ArticleRepositoryImpl() {
        super(Article.class);
    }
}
