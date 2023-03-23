package com.capstone.tvchat.api.article.controller;

import com.capstone.tvchat.api.article.domain.dto.ArticleRequest;
import com.capstone.tvchat.api.article.service.ArticleService;
import com.capstone.tvchat.common.BaseEntity.JsonResultData;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("게시글 기능 API")
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequest articleRequest) {
        articleService.createArticle(articleRequest);
        return ResponseEntity.ok(
                JsonResultData.successResultBuilder()
                        .build()
        );
    }
}
