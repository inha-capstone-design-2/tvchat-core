package com.capstone.tvchat.api.bbs.domain.dto.response;

import com.capstone.tvchat.api.member.auth.domain.dto.response.MemberResponseDto;
import com.capstone.tvchat.api.bbs.domain.entity.Article;
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
    private LocalDateTime createdTime;
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
}
