package com.capstone.tvchat.api.bbs.domain.entity;

import com.capstone.tvchat.api.member.domain.entity.Member;
import com.capstone.tvchat.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ARTICLE")
@NoArgsConstructor
public class Article extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "article_title")
    private String title;

    @Column(name = "article_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Article(Long id, String title, String content, Member member, Board board) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
        this.board = board;
    }
}
