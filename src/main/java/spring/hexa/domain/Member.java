package spring.hexa.domain;

import jakarta.persistence.*;
import lombok.*;

import static java.util.Objects.requireNonNull;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    private String nickname;

    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;



    public static Member create (MemberRegisterRequest memberRegisterRequest, PasswordEncoder passwordEncoder){
        Member member = new Member();

        member.email = new Email(memberRegisterRequest.email());
        member.nickname = requireNonNull(memberRegisterRequest.nickname());
        member.passwordHash = requireNonNull(passwordEncoder.encode(memberRegisterRequest.password()));
        member.status = MemberStatus.PENDING;

        return member;
    }

    public void activate () {
        if(this.status != MemberStatus.PENDING) {
            throw new IllegalStateException("PENDING 상태에서만 ACTIVE 상태로 변경할 수 있습니다.");
        }
        this.status = MemberStatus.ACTIVE;
    }

    public void deactivate () {
        if(this.status != MemberStatus.ACTIVE) {
            throw new IllegalStateException("ACTIVE 상태에서만 DEACTIVATE 상태로 변경할 수 있습니다.");
        }
        this.status = MemberStatus.DEACTIVATE;
    }

    public boolean verifyPassword (String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password,this.passwordHash);
    }

    public void changeNickname(String newNickname) {
        this.nickname = requireNonNull(newNickname);
    }

    public void changePassword(String newPassword, PasswordEncoder passwordEncoder) {
        this.passwordHash = passwordEncoder.encode(requireNonNull(newPassword));
    }

    public boolean isActive() {
        return this.status == MemberStatus.ACTIVE;
    }
}
