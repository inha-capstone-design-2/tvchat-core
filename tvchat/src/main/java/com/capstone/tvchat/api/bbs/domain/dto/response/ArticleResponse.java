package com.capstone.tvchat.api.bbs.domain.dto.response;

import com.capstone.tvchat.api.bbs.domain.entity.Article;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private Long boardId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;
    private Long createdBy;
    private String creatorName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedTime;
    private Long updatedBy;

    @Builder
    public ArticleResponse(Long id, String title, String content, Long boardId, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
    }

    public static ArticleResponse toResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .boardId(article.getBoard().getId())
                .createdTime(article.getCreatedDate())
                .createdBy(article.getCreatedBy())
                .updatedTime(article.getLastModifiedDate())
                .updatedBy(article.getUpdatedBy())
                .build();
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
