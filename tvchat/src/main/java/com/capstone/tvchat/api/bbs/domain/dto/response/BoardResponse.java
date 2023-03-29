package com.capstone.tvchat.api.bbs.domain.dto.response;

import com.capstone.tvchat.api.bbs.domain.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponse {
    private Long id;
    private String name;

    @Builder
    public BoardResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BoardResponse toResponse(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .build();
    }

    @Data
    @NoArgsConstructor
    public static class ArticleRequest {
        private String title;
        private String content;
        private Long memberId;
        private Long boardId;
    }
}
