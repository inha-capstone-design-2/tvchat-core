package com.capstone.tvchat.api.bbs.service;

import com.capstone.tvchat.api.bbs.domain.dto.request.BoardCreateRequest;
import com.capstone.tvchat.api.bbs.domain.dto.request.ModifyBoardRequest;
import com.capstone.tvchat.api.bbs.domain.dto.response.BoardResponse;
import com.capstone.tvchat.api.bbs.domain.entity.Board;
import com.capstone.tvchat.api.bbs.domain.enums.BoardErrorCode;
import com.capstone.tvchat.api.bbs.repository.BoardRepository;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.api.program.domain.enums.ProgramErrorCode;
import com.capstone.tvchat.api.program.repository.ProgramRepository;
import com.capstone.tvchat.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final ProgramRepository programRepository;

    @Transactional
    public Long createBoard(BoardCreateRequest boardCreateRequest) {
        Program program = programRepository.findById(boardCreateRequest.getProgramId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ProgramErrorCode.PROGRAM_NOT_FOUND.getMessage())
                        .errorCode(ProgramErrorCode.PROGRAM_NOT_FOUND.getCode())
                        .build());

        return boardRepository.save(
                BoardCreateRequest.createBoard(boardCreateRequest, program)
        ).getId();
    }

    public BoardResponse getBoard(Integer boardId) {
        return BoardResponse.toResponse(boardRepository.findById(boardId));
    }

    public List<BoardResponse> getAllBoard() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public Long modifyBoard(Long boardId, ModifyBoardRequest modifyBoardRequest) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(BoardErrorCode.BOARD_NOT_FOUND.getMessage())
                        .errorCode(BoardErrorCode.BOARD_NOT_FOUND.getCode())
                        .build());

        return null;
    }
}
