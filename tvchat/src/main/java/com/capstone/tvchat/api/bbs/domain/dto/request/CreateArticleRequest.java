package com.capstone.tvchat.api.bbs.domain.dto.request;

import com.capstone.tvchat.api.bbs.domain.entity.Article;
import com.capstone.tvchat.api.bbs.domain.entity.Board;
import com.capstone.tvchat.api.member.domain.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateArticleRequest {
    private String title;
    private String content;
    private Long memberId;
    private Long boardId;

    public static Article toEntity(CreateArticleRequest createArticleRequest, Member member, Board board) {
        return Article.createBuilder()
                .title(createArticleRequest.getTitle())
                .content(createArticleRequest.getContent())
                .member(member)
                .board(board)
                .build();
    }
}
