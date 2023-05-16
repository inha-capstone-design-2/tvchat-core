package com.capstone.tvchat.api.bbs.domain.entity;

import com.capstone.tvchat.api.bbs.domain.dto.request.ModifyArticleRequest;
import com.capstone.tvchat.common.domain.BaseEntity;
import com.capstone.tvchat.common.domain.enums.UseYn;
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
    @JoinColumn(name = "board_id")
    private Board board;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "use_yn")
    private UseYn useYn;

    @Builder
    public Article(Long id, String title, String content,  Board board, UseYn useYn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.board = board;
        this.useYn = useYn;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static Article create(String title, String content, Board board) {
        return Article.builder()
                .title(title)
                .content(content)
                .board(board)
                .useYn(UseYn.Y)
                .build();
    }

    public void modify(ModifyArticleRequest modifyArticleRequest) {
        this.title = modifyArticleRequest.getTitle();
        this.content = modifyArticleRequest.getContent();
    }

    public void delete() {
        this.useYn = UseYn.N;
    }
}
