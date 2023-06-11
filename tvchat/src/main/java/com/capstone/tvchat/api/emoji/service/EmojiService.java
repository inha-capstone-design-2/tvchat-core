package com.capstone.tvchat.api.emoji.service;

import com.capstone.tvchat.api.emoji.domain.dto.request.CreateEmojiRequest;
import com.capstone.tvchat.api.emoji.domain.dto.response.EmojiResponse;
import com.capstone.tvchat.api.emoji.domain.entity.Emoji;
import com.capstone.tvchat.api.emoji.domain.enums.EmojiErrorCode;
import com.capstone.tvchat.api.emoji.repository.EmojiRepository;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.api.program.domain.enums.ProgramErrorCode;
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
public class EmojiService {
    private final EmojiRepository emojiRepository;
    private final ProgramRepository programRepository;

    public List<EmojiResponse> getEmojiByProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> ApiException.builder()
                        .errorCode(ProgramErrorCode.PROGRAM_NOT_FOUND.getCode())
                        .errorMessage(ProgramErrorCode.PROGRAM_NOT_FOUND.getMessage())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return emojiRepository.findByProgram(program).stream()
                .map(EmojiResponse::toResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public Long createEmoji(CreateEmojiRequest createEmojiRequest) {
        return emojiRepository.save(Emoji.create(createEmojiRequest))
                .getId();
    }

    @Transactional
    public void deleteEmoji(Long emojiId) {
        Emoji emoji = emojiRepository.findById(emojiId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(EmojiErrorCode.EMOJI_NOT_FOUND.getMessage())
                        .errorCode(EmojiErrorCode.EMOJI_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        emoji.delete();
    }
}
