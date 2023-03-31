package com.capstone.tvchat.api.bbs.repository;

import com.capstone.tvchat.api.bbs.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findById(Integer boardId);
}
