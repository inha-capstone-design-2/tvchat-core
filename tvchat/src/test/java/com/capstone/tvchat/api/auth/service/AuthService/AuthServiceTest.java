package com.capstone.tvchat.api.auth.service.AuthService;

import com.capstone.tvchat.api.auth.domain.dto.MemberResponseDto.MemberResponseDto;
import com.capstone.tvchat.api.auth.domain.dto.MemberSignupDto.MemberSignupDto;
import com.capstone.tvchat.api.member.domain.entity.Member;
import com.capstone.tvchat.api.member.domain.entity.enumerate.Authority;
import com.capstone.tvchat.api.member.repository.MemberRepository;
import com.capstone.tvchat.common.enumerate.Yn;
import com.capstone.tvchat.common.provider.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class AuthServiceTest {

    private AuthService authService;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    Member FIXTURE_MEM_01 = Member.of()
            .email("tester@test.com")
            .nickname("tester")
            .password("encodingPassword")
            .authority(Authority.ROLE_USER)
            .useYn(Yn.Y)
            .build();

    MemberSignupDto FIXTURE_MEM_DTO_01 = MemberSignupDto.of()
            .email("tester@test.com")
            .nickname("tester")
            .password("tester")
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authService = new AuthService(authenticationManagerBuilder,passwordEncoder,memberRepository,redisTemplate,jwtTokenProvider);
    }

    @Test
    void signup() {
        given(passwordEncoder.encode(any())).willReturn(FIXTURE_MEM_01.getPassword());
        given(memberRepository.save(any())).willReturn(FIXTURE_MEM_01);
        MemberResponseDto result = authService.signup(FIXTURE_MEM_DTO_01);

        assertThat(result.getEmail()).isEqualTo(FIXTURE_MEM_01.getEmail());
    }
}