package com.capstone.tvchat.api.bbs.domain.dto.request;

import com.capstone.tvchat.api.bbs.domain.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModifyBoardRequest {
    private String boardName;
    private Long programId;
    private String imagePath;
    private String description;

    public void modifyBoard(Board board, ModifyBoardRequest modifyBoardRequest) {
        board.modifyBoard(modifyBoardRequest.getBoardName(), modifyBoardRequest.);
    }
}
