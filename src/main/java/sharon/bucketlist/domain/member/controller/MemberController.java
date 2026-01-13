package sharon.bucketlist.domain.member.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sharon.bucketlist.domain.member.dto.req.LoginRequest;
import sharon.bucketlist.domain.member.dto.req.SignUpRequest;
import sharon.bucketlist.domain.member.dto.res.LoginResponse;
import sharon.bucketlist.domain.member.service.MemberService;
import sharon.bucketlist.global.common.ApiResponse;
import sharon.bucketlist.global.common.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/signup")
    public ApiResponse<String> signup(
            @RequestBody @Valid SignUpRequest loginrequest
            ) {
        memberService.signup(loginrequest);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.REQUEST_OK,"성공적으로 회원가입되었습니다."
        );
    }

    @PostMapping("/api/v1/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest loginrequest
    ) {
        memberService.login(loginrequest);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.REQUEST_OK, memberService.login(loginrequest)
        );
    }
}
