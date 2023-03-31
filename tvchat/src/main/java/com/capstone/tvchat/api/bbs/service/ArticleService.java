package com.capstone.tvchat.api.bbs.service;

import com.capstone.tvchat.api.bbs.domain.dto.request.CreateArticleRequest;
import com.capstone.tvchat.api.bbs.domain.dto.request.ModifyArticleRequest;
import com.capstone.tvchat.api.bbs.domain.dto.response.ArticleResponse;
import com.capstone.tvchat.api.bbs.domain.entity.Board;
import com.capstone.tvchat.api.bbs.domain.enums.BoardErrorCode;
import com.capstone.tvchat.api.bbs.repository.ArticleRepository;
import com.capstone.tvchat.api.bbs.repository.BoardRepository;
import com.capstone.tvchat.api.member.domain.entity.Member;
import com.capstone.tvchat.api.member.domain.enums.MemberErrorCode;
import com.capstone.tvchat.api.member.repository.MemberRepository;
import com.capstone.tvchat.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long createArticle(CreateArticleRequest createArticleRequest) {
        Board board = boardRepository.findById(createArticleRequest.getBoardId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(BoardErrorCode.BOARD_NOT_FOUND.getMessage())
                        .errorCode(BoardErrorCode.BOARD_NOT_FOUND.getCode())
                        .build());

        Member member = memberRepository.findById(createArticleRequest.getMemberId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(MemberErrorCode.MEMBER_NOT_FOUND.getMessage())
                        .errorCode(MemberErrorCode.MEMBER_NOT_FOUND.getCode())
                        .build());

        return articleRepository.save(
                CreateArticleRequest.toEntity(createArticleRequest, member, board)
        ).getId();
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public ArticleResponse modifyArticle(Long articleId, ModifyArticleRequest modifyArticleRequest) {
        return null;
    }
}