package com.capstone.tvchat.api.auth.service.AuthService;

import com.capstone.tvchat.api.auth.domain.dto.MemberResponseDto.MemberResponseDto;
import com.capstone.tvchat.api.auth.domain.dto.MemberSignupDto.MemberSignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    public MemberResponseDto signup(MemberSignupDto memberSignupDto) {
        return null;
    }
}
