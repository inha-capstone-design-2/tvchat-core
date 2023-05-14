package com.capstone.tvchat.api.bookmark.domain.entity;

import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.common.domain.BaseEntity;
import com.capstone.tvchat.common.domain.enums.UseYn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "BOOKMARK")
@Where(clause = "use_yn = 'Y'")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn")
    private UseYn useYn;

    @Builder
    public Bookmark(Long id, Program program, UseYn useYn) {
        this.id = id;
        this.program = program;
        this.useYn = useYn;
    }

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public static Bookmark create(Program program) {
        return Bookmark.builder()
                .program(program)
                .useYn(UseYn.Y)
                .build();
    }

    public void delete() {
        this.useYn = UseYn.N;
    }
}
