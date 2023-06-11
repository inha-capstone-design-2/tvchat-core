package com.capstone.tvchat.api.emoji.controller;

import com.capstone.tvchat.api.emoji.domain.dto.request.CreateEmojiRequest;
import com.capstone.tvchat.api.emoji.service.EmojiService;
import com.capstone.tvchat.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("이모지 API Controller")
@RequestMapping("/api/emoji")
public class EmojiController {
    private final EmojiService emojiService;

    @GetMapping("/{program-id}")
    public ResponseEntity<?> getEmojiByProgram(@RequestParam(name = "program-id")Long programId) {
        return ResponseHandler.generate()
                .data(emojiService.getEmojiByProgram(programId))
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/")
    public ResponseEntity<?> createEmoji(@RequestBody CreateEmojiRequest createEmojiRequest) {
        return ResponseHandler.generate()
                .data(emojiService.createEmoji(createEmojiRequest))
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{emoji-id}")
    public ResponseEntity<?> deleteEmoji(@RequestParam(name = "emoji-id")Long emojiId) {
        emojiService.deleteEmoji(emojiId);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }
}
