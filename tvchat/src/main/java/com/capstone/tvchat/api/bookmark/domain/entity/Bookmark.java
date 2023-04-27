package com.capstone.tvchat.api.bookmark.domain.entity;

import com.capstone.tvchat.api.member.domain.entity.Member;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.common.domain.BaseEntity;
import com.capstone.tvchat.common.domain.enums.UseYn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "BOOKMARK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn")
    private UseYn useYn;

    @Builder
    public Bookmark(Long id, Member member, Program program, UseYn useYn) {
        this.id = id;
        this.member = member;
        this.program = program;
        this.useYn = useYn;
    }

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public static Bookmark create( Member member, Program program) {
        return Bookmark.builder()
                .member(member)
                .program(program)
                .useYn(UseYn.Y)
                .build();
    }
}
