package sharon.bucketlist.domain.member.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sharon.bucketlist.domain.member.dto.req.LoginRequest;
import sharon.bucketlist.domain.member.dto.req.SignUpRequest;
import sharon.bucketlist.domain.member.dto.res.LoginResponse;
import sharon.bucketlist.domain.member.entity.Member;
import sharon.bucketlist.domain.member.enums.Role;
import sharon.bucketlist.domain.member.enums.Status;
import sharon.bucketlist.domain.member.exception.code.MemberErrorCode;
import sharon.bucketlist.domain.member.repository.MemberRepository;
import sharon.bucketlist.global.common.exception.GeneralException;
import sharon.bucketlist.global.security.CustomUserDetails;
import sharon.bucketlist.global.utils.JwtTokenProvider;

@Service
@Transactional
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signup(SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new GeneralException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        Member member = toMember(signUpRequest, encodedPassword);

        memberRepository.save(member);


    }
    public LoginResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail()).
                orElseThrow(() -> new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new GeneralException(MemberErrorCode.UNAUTHORIZED);
        }

        CustomUserDetails userDetails = new CustomUserDetails(member);

        String token = jwtTokenProvider.createAccessToken(userDetails);

        return new LoginResponse().builder()
                .accessToken(token)
                .build();

    }

    public static Member toMember(SignUpRequest request, String encodedPassword) {
        return Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(encodedPassword)
                .role(Role.ROLE_USER)// 기본 권한
                .status(Status.ACTIVE)
                .build();
    }

}
