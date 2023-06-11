package com.capstone.tvchat.api.bbs.service;

import com.capstone.tvchat.api.bbs.domain.dto.request.CreateArticleRequest;
import com.capstone.tvchat.api.bbs.domain.dto.request.ModifyArticleRequest;
import com.capstone.tvchat.api.bbs.domain.dto.response.ArticleResponse;
import com.capstone.tvchat.api.bbs.domain.entity.Article;
import com.capstone.tvchat.api.bbs.domain.entity.Board;
import com.capstone.tvchat.api.bbs.domain.enums.ArticleErrorCode;
import com.capstone.tvchat.api.bbs.domain.enums.BoardErrorCode;
import com.capstone.tvchat.api.bbs.repository.ArticleRepository;
import com.capstone.tvchat.api.bbs.repository.BoardRepository;
import com.capstone.tvchat.api.member.domain.enums.MemberErrorCode;
import com.capstone.tvchat.api.member.repository.MemberRepository;
import com.capstone.tvchat.common.exception.ApiException;
import com.capstone.tvchat.common.result.ResponseHandler;
import com.capstone.tvchat.common.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createArticle(CreateArticleRequest createArticleRequest) {
        Board board = boardRepository.findById(createArticleRequest.getBoardId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(BoardErrorCode.BOARD_NOT_FOUND.getMessage())
                        .errorCode(BoardErrorCode.BOARD_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return articleRepository.save(
                CreateArticleRequest.toEntity(createArticleRequest, board)
        ).getId();
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ArticleErrorCode.ARTICLE_NOT_FOUND.getMessage())
                        .errorCode(ArticleErrorCode.ARTICLE_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        article.delete();
    }

    @Transactional
    public ArticleResponse modifyArticle(Long articleId, ModifyArticleRequest modifyArticleRequest) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ArticleErrorCode.ARTICLE_NOT_FOUND.getMessage())
                        .errorCode(ArticleErrorCode.ARTICLE_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        if(SecurityUtil.isOwned(article.getCreatedBy())) {
            ResponseHandler.failResultGenerate()
                    .errorMessage(MemberErrorCode.NO_PERMISSION.getMessage())
                    .errorCode(MemberErrorCode.NO_PERMISSION.getCode())
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        article.modify(modifyArticleRequest);

        return ArticleResponse.toResponse(article);
    }

    public List<ArticleResponse> getArticleByBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(BoardErrorCode.BOARD_NOT_FOUND.getMessage())
                        .errorCode(BoardErrorCode.BOARD_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        List<ArticleResponse> articleResponseList =  articleRepository.findByBoard(board)
                .stream().map(ArticleResponse::toResponse)
                .collect(Collectors.toList());

        for (ArticleResponse articleResponse:
             articleResponseList) {
            articleResponse.setCreatorName(
                    memberRepository.findById(articleResponse.getCreatedBy())
                            .orElseThrow(() -> ApiException.builder()
                                    .errorMessage(MemberErrorCode.MEMBER_NOT_FOUND.getMessage())
                                    .errorCode(MemberErrorCode.MEMBER_NOT_FOUND.getCode())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .build())
                            .getNickname());
        }

        return articleResponseList;
    }

    public ArticleResponse getArticle(Long articleId) {
        return ArticleResponse.toResponse(
                articleRepository.findById(articleId)
                        .orElseThrow(() -> ApiException.builder()
                                .errorMessage(ArticleErrorCode.ARTICLE_NOT_FOUND.getMessage())
                                .errorCode(ArticleErrorCode.ARTICLE_NOT_FOUND.getCode())
                                .status(HttpStatus.BAD_REQUEST)
                                .build())
        );
    }
}