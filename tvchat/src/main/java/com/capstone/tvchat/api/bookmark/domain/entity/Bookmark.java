package com.capstone.tvchat.api.bookmark.domain.entity;

import com.capstone.tvchat.api.member.domain.entity.Member;
import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "BOOKMARK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {
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

    @Builder
    public Bookmark(Member member, Program program) {
        this.member = member;
        this.program = program;
    }
}
