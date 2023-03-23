package com.capstone.tvchat.api.board.domain.entity;

import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "BOARD")
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @Builder
    public Board(Long id, String name, Program program) {
        this.id = id;
        this.name = name;
        this.program = program;
    }
}
