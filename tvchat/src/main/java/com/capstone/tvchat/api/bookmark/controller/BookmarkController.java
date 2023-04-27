package com.capstone.tvchat.api.bookmark.controller;

import com.capstone.tvchat.api.bookmark.domain.dto.request.CreateBookmarkRequest;
import com.capstone.tvchat.api.bookmark.service.BookmarkService;
import com.capstone.tvchat.api.member.domain.enumerate.Authority;
import com.capstone.tvchat.common.domain.JsonResultData;
import com.capstone.tvchat.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("Bookmark 관리 API")
@RequestMapping("/api/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @ApiOperation("Bookmark 생성 API")
    @PostMapping("/")
    public ResponseEntity<?> createBookmark(@RequestBody CreateBookmarkRequest createBookmarkRequest) {
        return ResponseHandler.generate()
                .data(bookmarkService.createBookmark(createBookmarkRequest))
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation("Bookmark 삭제 API")
    @DeleteMapping("/{bookmark-id}")
    public ResponseEntity<?> deleteBookmark(@RequestParam(name = "bookmark-id")Long bookmarkId) {
        bookmarkService.deleteBookmark(bookmarkId);

        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("Bookmark 조회 API")
    @GetMapping("/")
    public ResponseEntity<?> getBookmark() {
        return ResponseHandler.generate()
                .data(bookmarkService.getBookmark(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                ))
                .status(HttpStatus.OK)
                .build();
    }
}
