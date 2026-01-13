package sharon.bucketlist.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sharon.bucketlist.domain.member.entity.Member;
import sharon.bucketlist.domain.member.exception.code.MemberErrorCode;
import sharon.bucketlist.domain.member.repository.MemberRepository;
import sharon.bucketlist.global.common.exception.GeneralException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public CustomUserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(member);
    }
}