package com.capstone.tvchat.api.bbs.domain.entity;

import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.common.domain.BaseEntity;
import com.capstone.tvchat.common.domain.enums.UseYn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "BOARD")
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "use_yn")
    private UseYn useYn;

    @Builder
    public Board(Long id, String name, Program program, String imagePath, String description, UseYn useYn) {
        this.id = id;
        this.name = name;
        this.program = program;
        this.imagePath = imagePath;
        this.description = description;
        this.useYn = useYn;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static Board create(String name, Program program, String imagePath, String description) {
        return Board.builder()
                .name(name)
                .program(program)
                .imagePath(imagePath)
                .description(description)
                .useYn(UseYn.Y)
                .build();
    }

    public void modifyBoard(String name, Program program, String imagePath, String description) {
        this.name = name;
        this.program = program;
        this.imagePath = imagePath;
        this.description = description;
    }

    public void deActivate() {
        this.useYn = UseYn.N;
    }

    public void activate() {
        this.useYn = UseYn.Y;
    }
}
