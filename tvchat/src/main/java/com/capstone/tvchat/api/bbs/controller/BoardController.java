package com.capstone.tvchat.api.bbs.controller;

import com.capstone.tvchat.api.bbs.domain.dto.request.BoardCreateRequest;
import com.capstone.tvchat.api.bbs.domain.dto.request.ModifyBoardRequest;
import com.capstone.tvchat.api.bbs.service.BoardService;
import com.capstone.tvchat.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api("게시판 기능 API")
@RequestMapping("/api/bbs/board")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation("게시판 생성 API")
    @PostMapping("/")
    public ResponseEntity<?> createBoard(@RequestBody BoardCreateRequest boardCreateRequest) {
        Long boardId = boardService.createBoard(boardCreateRequest);

        return ResponseHandler.generate()
                .data(boardId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation("모든 게시판 조회 API")
    @GetMapping("/")
    public ResponseEntity<?> getAllBoard() {
        return ResponseHandler.generate()
                .data(boardService.getAllBoard())
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("게시판 삭제 API")
    @DeleteMapping("/{board-id}")
    public ResponseEntity<?> deleteBoard(@RequestParam(name = "board-id")Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("게시판 수정 API")
    @PatchMapping("/{board-id}")
    public ResponseEntity<?> modifyBoard(@RequestParam(name = "board-id")Long boardId,
                                         @RequestBody ModifyBoardRequest modifyBoardRequest) {
        return ResponseHandler.generate()
                .data(boardService.modifyBoard(boardId, modifyBoardRequest))
                .status(HttpStatus.OK)
                .build();
    }
}
