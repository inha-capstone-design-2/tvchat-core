package com.capstone.tvchat.api.auth.service.AuthService;

import com.capstone.tvchat.api.auth.domain.dto.MemberLoginDto;
import com.capstone.tvchat.api.auth.domain.dto.MemberResponseDto.MemberResponseDto;
import com.capstone.tvchat.api.auth.domain.dto.MemberSignupDto.MemberSignupDto;
import com.capstone.tvchat.api.auth.domain.dto.PasswordUpdateRequest;
import com.capstone.tvchat.api.auth.domain.dto.TokenDto;
import com.capstone.tvchat.api.auth.domain.dto.TokenRequestDto;
import com.capstone.tvchat.api.member.domain.entity.Member;
import com.capstone.tvchat.api.member.repository.MemberRepository;
import com.capstone.tvchat.common.exception.ApiException;
import com.capstone.tvchat.common.exception.CustomAuthException;
import com.capstone.tvchat.common.exception.code.AuthErrorCode;
import com.capstone.tvchat.common.exception.code.MemberErrorCode;
import com.capstone.tvchat.common.provider.JwtTokenProvider;
import com.capstone.tvchat.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final StringRedisTemplate redisTemplate;

    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public MemberResponseDto signup(MemberSignupDto memberSignupDto) {
        Member member = MemberSignupDto.toMember(memberSignupDto, passwordEncoder);
        member = memberRepository.save(member);
        return MemberResponseDto.toDto(member);
    }

    public TokenDto login(MemberLoginDto memberLoginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = memberLoginDto.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return getTokenDto(authentication);
    }

    private TokenDto getTokenDto(Authentication authentication) {
        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(authentication);

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(),
                        tokenDto.getRefreshToken(),
                        tokenDto.getAccessTokenExpiresIn(),
                        TimeUnit.MILLISECONDS);

        return tokenDto;
    }

    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.NOT_VALID_TOKEN.getCode())
                    .errorMessage(AuthErrorCode.NOT_VALID_TOKEN.getMessage())
                    .build());
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        String refreshToken = redisTemplate.opsForValue().get("RT:" + authentication.getName());
        if (refreshToken == null || !refreshToken.equals(tokenRequestDto.getRefreshToken())) {
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.NOT_MATCH_TOKEN_INFO.getCode())
                    .errorMessage(AuthErrorCode.NOT_MATCH_TOKEN_INFO.getMessage())
                    .build());
        }

        return getTokenDto(authentication);
    }

    public void logout(TokenRequestDto tokenRequestDto) {
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getAccessToken())) {
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.NOT_VALID_TOKEN.getCode())
                    .errorMessage(AuthErrorCode.NOT_VALID_TOKEN.getMessage())
                    .build());
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            redisTemplate.delete("RT:" + authentication.getName());
        }

        Long expiration = jwtTokenProvider.getExpiration(tokenRequestDto.getAccessToken());
        redisTemplate.opsForValue()
                .set(tokenRequestDto.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);
    }

    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) {
        Member member = memberRepository.findByEmail(passwordUpdateRequest.getEmail())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .build());

        member.changePassword(passwordEncoder.encode(passwordUpdateRequest.getPassword()));
    }
}
