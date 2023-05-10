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
@Table(name = "EPISODE")
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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "use_yn")
    private UseYn useYn;

    @Builder
    public Episode(Long id, Program program, String description, LocalDateTime startTime, LocalDateTime endTime, UseYn useYn) {
        this.id = id;
        this.program = program;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.useYn = useYn;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static Episode create(Program program, String description, LocalDateTime startTime, LocalDateTime endTime) {
        return Episode.builder()
                .program(program)
                .description(description)
                .startTime(startTime)
                .endTime(endTime)
                .useYn(UseYn.Y)
                .build();
    }

    public void delete() {
        this.useYn=UseYn.N;
    }
}
