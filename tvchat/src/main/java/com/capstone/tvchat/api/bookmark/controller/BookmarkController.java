package com.capstone.tvchat.api.bookmark.controller;

import com.capstone.tvchat.api.bookmark.domain.dto.request.CreateBookmarkRequest;
import com.capstone.tvchat.api.bookmark.service.BookmarkService;
import com.capstone.tvchat.common.domain.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookmarkService.createBookmark(createBookmarkRequest));
    }

    @ApiOperation("Bookmark 삭제 API")
    @DeleteMapping("/{bookmark-id}")
    public ResponseEntity<?> deleteBookmark(@RequestParam(name = "bookmark-id")Long bookmarkId) {
        bookmarkService.deleteBookmark(bookmarkId);

        return ResponseEntity.ok(
                JsonResultData.successResultBuilder()
                        .data(null)
                        .build()
        );
    }

}
