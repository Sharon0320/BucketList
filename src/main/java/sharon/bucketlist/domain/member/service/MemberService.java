package sharon.bucketlist.domain.member.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sharon.bucketlist.domain.member.dto.req.LoginRequest;
import sharon.bucketlist.domain.member.entity.Member;
import sharon.bucketlist.domain.member.enums.Role;
import sharon.bucketlist.domain.member.exception.code.MemberErrorCode;
import sharon.bucketlist.domain.member.repository.MemberRepository;
import sharon.bucketlist.global.common.exception.GeneralException;

@Service
@Transactional
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(LoginRequest loginRequest) {
        if(memberRepository.existsByEmail(loginRequest.getEmail())) {
            throw new GeneralException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());

        Member member = toMember(loginRequest, encodedPassword);
        memberRepository.save(member);


    }

    public static Member toMember(LoginRequest request, String encodedPassword) {
        return Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(encodedPassword)
                .role(Role.ROLE_USER)// 기본 권한
                .build();
    }

}
