package com.capstone.tvchat.api.bbs.controller;

import com.capstone.tvchat.api.bbs.domain.dto.request.CreateArticleRequest;
import com.capstone.tvchat.api.bbs.domain.dto.request.ModifyArticleRequest;
import com.capstone.tvchat.api.bbs.service.ArticleService;
import com.capstone.tvchat.common.domain.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("게시글 기능 API")
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    @ApiOperation("Article 생성 API")
    @PostMapping("/")
    public ResponseEntity<?> createArticle(@RequestBody CreateArticleRequest createArticleRequest) {
        articleService.createArticle(createArticleRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        JsonResultData.successResultBuilder()
                .build()
        );
    }

    @ApiOperation("Article 삭제 API")
    @DeleteMapping("/{article-id}")
    public ResponseEntity<?> deleteArticle(@RequestParam(name = "article-id")Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok(
                JsonResultData.successResultBuilder()
                        .data(null)
                        .build()
        );
    }

    @ApiOperation("Article 수정 API")
    @PatchMapping("/{article-id}")
    public ResponseEntity<?> modifyArticle(@RequestParam(name = "article-id")Long articleId,
                                           @RequestBody ModifyArticleRequest modifyArticleRequest) {
        return ResponseEntity.ok(
                JsonResultData.successResultBuilder()
                        .data(articleService.modifyArticle(articleId, modifyArticleRequest))
                        .build()
        );
    }
}
