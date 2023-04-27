package com.capstone.tvchat.api.bookmark.service;

import com.capstone.tvchat.api.bookmark.domain.dto.request.CreateBookmarkRequest;
import com.capstone.tvchat.api.bookmark.domain.dto.response.BookmarkResponse;
import com.capstone.tvchat.api.bookmark.domain.entity.Bookmark;
import com.capstone.tvchat.api.bookmark.repository.BookmarkRepository;
import com.capstone.tvchat.api.channel.domain.enums.ChannelErrorCode;
import com.capstone.tvchat.api.member.domain.entity.Member;
import com.capstone.tvchat.api.member.domain.enums.MemberErrorCode;
import com.capstone.tvchat.api.member.repository.MemberRepository;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.api.program.repository.ProgramRepository;
import com.capstone.tvchat.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final ProgramRepository programRepository;

    public Long createBookmark(CreateBookmarkRequest createBookmarkRequest) {
        Member member = memberRepository.findById(createBookmarkRequest.getMemberId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(MemberErrorCode.MEMBER_NOT_FOUND.getMessage())
                        .errorCode(MemberErrorCode.MEMBER_NOT_FOUND.getCode())
                        .build());

        Program program = programRepository.findById(createBookmarkRequest.getProgramId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ChannelErrorCode.CHANNEL_NOT_FOUND.getMessage())
                        .errorCode(ChannelErrorCode.CHANNEL_NOT_FOUND.getCode())
                        .build());

        return bookmarkRepository.save(
                Bookmark.builder()
                        .program(program)
                        .member(member)
                        .build()).getId();
    }

    public void deleteBookmark(Long bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }

    public List<BookmarkResponse> getBookmark(String username) {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(MemberErrorCode.MEMBER_NOT_FOUND.getMessage())
                        .errorCode(MemberErrorCode.MEMBER_NOT_FOUND.getCode())
                        .status(HttpStatus.NOT_FOUND)
                        .build());

        return bookmarkRepository.findByMember(member).stream()
                .map(BookmarkResponse::toResponse).
                collect(Collectors.toList());
    }
}
