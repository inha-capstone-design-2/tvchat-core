package com.capstone.tvchat.api.article.domain.entity;

import com.capstone.tvchat.api.board.domain.entity.Board;
import com.capstone.tvchat.api.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ARTICLE")
@NoArgsConstructor
public class Article {
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
