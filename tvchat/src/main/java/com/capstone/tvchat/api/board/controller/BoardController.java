package com.capstone.tvchat.api.board.controller;

import com.capstone.tvchat.api.board.service.BoardService;
import com.capstone.tvchat.common.BaseEntity.JsonResultData;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api("게시판 기능 API")
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{board-id}")
    public ResponseEntity<?> getBoard(@RequestParam("board-id") Integer boardId) {
        return ResponseEntity.ok(
                JsonResultData.successResultBuilder()
                        .data(boardService.getBoard(boardId))
                        .build()
        );
    }
}
