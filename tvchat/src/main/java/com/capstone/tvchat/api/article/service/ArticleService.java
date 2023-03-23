package com.capstone.tvchat.api.article.service;

import com.capstone.tvchat.api.article.domain.dto.ArticleRequest;
import com.capstone.tvchat.api.article.repository.ArticleRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void createArticle(ArticleRequest articleRequest) {

    }
}
