package com.capstone.tvchat.api.bbs.domain.dto.request;

import com.capstone.tvchat.api.bbs.domain.entity.Board;
import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModifyBoardRequest {
    private String boardName;
    private Long programId;
    private String imagePath;
    private String description;

    public void modifyBoard(Board board, ModifyBoardRequest modifyBoardRequest, Program program) {
        board.modifyBoard(modifyBoardRequest.getBoardName(), program, modifyBoardRequest.getImagePath(), modifyBoardRequest.getDescription());
    }
}