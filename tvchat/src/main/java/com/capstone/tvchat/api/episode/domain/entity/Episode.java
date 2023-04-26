package com.capstone.tvchat.api.episode.domain.entity;

import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.common.domain.BaseEntity;
import com.capstone.tvchat.common.domain.enums.UseYn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Episode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episode_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "description")
    private String description;

    @Column(name = "broadcast_start_time")
    private LocalDateTime startTime;

    @Column(name = "broadcast_end_time")
    private LocalDateTime endTime;

    @Builder
    public Episode(Long id, Program program, String description, LocalDateTime startTime, LocalDateTime endTime, UseYn useYn) {
        this.id = id;
        this.program = program;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
