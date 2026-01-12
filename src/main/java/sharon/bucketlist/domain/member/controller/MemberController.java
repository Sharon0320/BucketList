package sharon.bucketlist.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sharon.bucketlist.domain.member.dto.req.LoginRequest;
import sharon.bucketlist.domain.member.service.MemberService;
import sharon.bucketlist.global.common.ApiResponse;
import sharon.bucketlist.global.common.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/signup")
    public ApiResponse<String> signup(
            @RequestBody LoginRequest loginrequst
            ) {
        memberService.signup(loginrequst);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.REQUEST_OK,"성공적으로 회원가입되었습니다."
        );
    }
}
