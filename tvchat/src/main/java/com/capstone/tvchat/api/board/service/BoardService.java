package com.capstone.tvchat.api.board.service;

import com.capstone.tvchat.api.board.domain.dto.BoardResponse;
import com.capstone.tvchat.api.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.plugins.SpringIntegrationPluginNotPresentInClassPathCondition;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponse getBoard(Integer boardId) {
        return BoardResponse.toResponse(boardRepository.findById(boardId));
    }
}
