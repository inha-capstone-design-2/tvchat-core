package com.capstone.tvchat.api.auth.controller;

import com.capstone.tvchat.api.auth.domain.dto.MemberResponseDto.MemberResponseDto;
import com.capstone.tvchat.api.auth.domain.dto.MemberSignupDto.MemberSignupDto;
import com.capstone.tvchat.api.auth.service.AuthService.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
@Api("Auth API")
public class AuthController {

    private final String BASIC_PREFIX = "Basic ";
    private final AuthService authService;

    @ApiOperation(value = "회원가입 API")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @Valid @RequestBody MemberSignupDto memberSignupDto) {
        if(authorization != null) {
            String authBasic = authorization.substring(BASIC_PREFIX.length());

            String decodedAuthBasic = new String(Base64.getDecoder().decode(authBasic), StandardCharsets.UTF_8);
            String[] authUserInfo = decodedAuthBasic.split(":");

            String email = authUserInfo[0];
            String password = authUserInfo[1];

            memberSignupDto.setEmail(email);
            memberSignupDto.setPassword(password);

            MemberResponseDto memberResponseDto = authService.signup(memberJoinDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(JsonResultData.successResultBuilder()
                            .data(memberResponseDto)
                            .build());
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JsonResultData.failResultBuilder()
                            .errorMessage(MemberErrorCode.ENTERED_EMAIL_AND_PASSWORD.getMessage())
                            .errorCode(MemberErrorCode.ENTERED_EMAIL_AND_PASSWORD.getCode())
                            .build());
        }
    }
}
