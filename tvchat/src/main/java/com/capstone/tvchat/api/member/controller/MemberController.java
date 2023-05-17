package com.capstone.tvchat.api.member.controller;

import com.capstone.tvchat.api.member.service.MemberService;
import com.capstone.tvchat.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("멤버 기능 API")
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @ApiOperation("멤버 정보 조회 API")
    @GetMapping("/{member-id}")
    public ResponseEntity<?> getMemberInfo(@RequestParam(name = "member-id") Long memberId) {
        return ResponseHandler.generate()
                .data(memberService.getMemberInfo(memberId))
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("닉네임 변경 API")
    @PostMapping("/{nickname}")
    public ResponseEntity<?> changeMemberNickname(@RequestParam(name = "nickname") String nickname) {
        return ResponseHandler.generate()
                .data(memberService.changeMemberNickname(nickname))
                .status(HttpStatus.OK)
                .build();
    }
}
