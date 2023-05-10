package com.capstone.tvchat.api.bbs.domain.dto.response;

import com.capstone.tvchat.api.bbs.domain.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponse {
    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
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
