package com.capstone.tvchat.api.emoji.repository;

import com.capstone.tvchat.api.emoji.domain.entity.Emoji;
import com.capstone.tvchat.api.program.domain.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {
    List<Emoji> findByProgram(Program program);
}
