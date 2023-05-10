package com.capstone.tvchat.api.bbs.domain.dto.request;

import com.capstone.tvchat.api.bbs.domain.entity.Board;
import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardCreateRequest {
    private String boardName;
    private Long programId;
    private Program program;
    private String imagePath;
    private String description;

    public static Board toEntity(BoardCreateRequest boardCreateRequest) {
        return Board.createBuilder()
                .name(boardCreateRequest.getBoardName())
                .program(boardCreateRequest.getProgram())
                .imagePath(boardCreateRequest.getImagePath())
                .description(boardCreateRequest.getDescription())
                .build();
    }
}
