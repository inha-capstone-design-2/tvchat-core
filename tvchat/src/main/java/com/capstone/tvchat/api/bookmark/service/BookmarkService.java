package com.capstone.tvchat.api.bookmark.service;

import com.capstone.tvchat.api.bookmark.domain.dto.request.CreateBookmarkRequest;
import com.capstone.tvchat.api.bookmark.domain.dto.response.BookmarkResponse;
import com.capstone.tvchat.api.bookmark.domain.entity.Bookmark;
import com.capstone.tvchat.api.bookmark.domain.enums.BookmarkErrorCode;
import com.capstone.tvchat.api.bookmark.repository.BookmarkRepository;
import com.capstone.tvchat.api.channel.domain.enums.ChannelErrorCode;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.api.program.repository.ProgramRepository;
import com.capstone.tvchat.common.exception.ApiException;
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
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ProgramRepository programRepository;

    @Transactional
    public Long createBookmark(CreateBookmarkRequest createBookmarkRequest) {

        Program program = programRepository.findById(createBookmarkRequest.getProgramId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ChannelErrorCode.CHANNEL_NOT_FOUND.getMessage())
                        .errorCode(ChannelErrorCode.CHANNEL_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return bookmarkRepository.save(
                Bookmark.createBuilder()
                        .program(program)
                        .build())
                .getId();
    }

    @Transactional
    public void deleteBookmark(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(BookmarkErrorCode.BOOKMARK_NOT_FOUND.getMessage())
                        .errorCode(BookmarkErrorCode.BOOKMARK_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        bookmark.delete();
    }

    public List<BookmarkResponse> getBookmark() {
        return bookmarkRepository.findByCreatedBy(SecurityUtil.getCurrentMemberId()).stream()
                .map(BookmarkResponse::toResponse)
                .collect(Collectors.toList());
    }
}
