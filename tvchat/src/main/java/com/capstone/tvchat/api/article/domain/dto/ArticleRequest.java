package com.capstone.tvchat.api.article.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleRequest {
    private String title;
    private String content;
    private Long memberId;
    private Long boardId;
}
