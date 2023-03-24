package com.capstone.tvchat.api.program.repository;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import com.capstone.tvchat.api.program.domain.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByChannel(Channel channel);
}
