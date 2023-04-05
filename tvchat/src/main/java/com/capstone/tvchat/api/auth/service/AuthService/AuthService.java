package com.capstone.tvchat.api.auth.service.AuthService;

import com.capstone.tvchat.api.auth.domain.dto.MemberResponseDto.MemberResponseDto;
import com.capstone.tvchat.api.auth.domain.dto.MemberSignupDto.MemberSignupDto;
import com.capstone.tvchat.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final StringRedisTemplate redisTemplate;


    @Transactional
    public MemberResponseDto signup(MemberSignupDto memberSignupDto) {
        return null;
    }
}
