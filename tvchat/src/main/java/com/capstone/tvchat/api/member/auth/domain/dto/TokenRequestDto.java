package com.capstone.tvchat.api.member.auth.domain.dto;

import lombok.Getter;

@Getter
public class TokenRequestDto {

    private String accessToken;

    private String refreshToken;
}
