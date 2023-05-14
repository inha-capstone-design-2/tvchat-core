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
    private Long programId;
    private String imagePath;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedTime;

    @Builder
    public BoardResponse(Long id, String name, Long programId, String imagePath, String description, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.name = name;
        this.programId = programId;
        this.imagePath = imagePath;
        this.description = description;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public static BoardResponse toResponse(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .programId(board.getProgram().getId())
                .imagePath(board.getImagePath())
                .description(board.getDescription())
                .createdTime(board.getCreatedDate())
                .updatedTime(board.getLastModifiedDate())
                .build();
    }
}
