package com.capstone.tvchat.api.auth.domain.dto;

import lombok.Getter;

@Getter
public class TokenRequestDto {
    private String accessToken;

    private String refreshToken;
}
