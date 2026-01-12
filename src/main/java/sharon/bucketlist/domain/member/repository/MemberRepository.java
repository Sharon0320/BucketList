package sharon.bucketlist.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharon.bucketlist.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByEmailAndPassword(String email, String password);
    Member findByEmail(String email);
    Boolean existsByEmail(String email);
}
