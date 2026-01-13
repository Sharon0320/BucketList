package sharon.bucketlist.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharon.bucketlist.domain.member.entity.Member;
import sharon.bucketlist.global.security.CustomUserDetails;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmailAndPassword(String email, String password);
    Boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);

}
