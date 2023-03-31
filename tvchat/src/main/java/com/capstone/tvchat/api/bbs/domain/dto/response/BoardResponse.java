package com.capstone.tvchat.api.bbs.domain.dto.response;

import com.capstone.tvchat.api.bbs.domain.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponse {
    private Long id;
    private String name;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @Builder
    public BoardResponse(Long id, String name, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.name = name;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public static BoardResponse toResponse(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .createdTime(board.getCreatedDate())
                .updatedTime(board.getLastModifiedDate())
                .build();
    }
}
