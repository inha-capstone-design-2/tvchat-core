package com.capstone.tvchat.api.bbs.domain.dto.request;

import com.capstone.tvchat.api.bbs.domain.entity.Board;
import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardCreateRequest {
    private Long boardId;
    private String boardName;
    private Long programId;
    private String imagePath;
    private String description;

    public static Board createBoard(BoardCreateRequest boardCreateRequest, Program program) {
        return Board.builder()
                .name(boardCreateRequest.getBoardName())
                .program(program)
                .imagePath(boardCreateRequest.getImagePath())
                .description(boardCreateRequest.getDescription())
                .build();
    }
}
