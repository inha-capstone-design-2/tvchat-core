package com.capstone.tvchat.api.board.repository;

import com.capstone.tvchat.api.board.domain.entity.Board;
import io.lettuce.core.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findById(Integer boardId);
}
