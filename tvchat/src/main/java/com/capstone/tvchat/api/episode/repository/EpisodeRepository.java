package com.capstone.tvchat.api.episode.repository;

import com.capstone.tvchat.api.episode.domain.dto.response.EpisodeResponse;
import com.capstone.tvchat.api.episode.domain.entity.Episode;
import com.capstone.tvchat.api.program.domain.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<EpisodeResponse> findByProgram(Program program);
}
