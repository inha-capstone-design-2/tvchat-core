package com.capstone.tvchat.api.bbs.domain.dto.response;

import com.capstone.tvchat.api.auth.domain.dto.MemberResponseDto.MemberResponseDto;
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
    private MemberResponseDto memberResponse;
    private BoardResponse boardResponse;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedTime;

    @Builder
    public ArticleResponse(Article article, MemberResponseDto memberResponseDto, BoardResponse boardResponse) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.memberResponse = memberResponseDto;
        this.boardResponse = boardResponse;
        this.createdTime = article.getCreatedDate();
        this.updatedTime = article.getLastModifiedDate();
    }

    public static ArticleResponse toResponse(Article article) {
        return ArticleResponse.builder()
                .article(article)
                .memberResponseDto(MemberResponseDto.toDto(article.getMember()))
                .boardResponse(BoardResponse.toResponse(article.getBoard()))
                .build();
    }
}
