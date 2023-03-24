package com.capstone.tvchat.api.bbs.domain.dto;

import com.capstone.tvchat.api.bbs.domain.entity.Board;
import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardCreateRequest {
    private Long boardId;
    private String boardName;
    private Long programId;
    private String imagePath;

    @Builder
    public BoardCreateRequest(Long boardId, String boardName, Long programId) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.programId = programId;
    }

    public static Board createBoard(BoardCreateRequest boardCreateRequest, Program program) {
        return Board.builder()
                .name(boardCreateRequest.getBoardName())
                .program(program)
                .imagePath(boardCreateRequest.getImagePath())
                .build();
    }
}
