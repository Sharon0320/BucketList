package sharon.bucketlist.domain.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import sharon.bucketlist.domain.member.enums.Role;
import sharon.bucketlist.domain.member.enums.Status;
import sharon.bucketlist.global.entity.BaseEntity;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "password" , nullable = false, length = 255)
    private String password;

    @Email
    @Column(name = "email" , nullable = false, length = 255)
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
