package spring.hexa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import static java.util.Objects.requireNonNull;


/**
 * jpa를 사용하도록 MemberRepository라는 required 인터페이스를 구현했기 때문에
 * @Entity 를 붙여 Member클래스가 jpa가 관리해야 할 엔티티임을 표시한다.
 * 이 경우 반드시 고유 식별자인 @Id가 필요하다. 또한 해당 클래스의 기본 생성자가 PRIVATE면 안된다.
 */
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NaturalIdCache
public class Member {

    /**
     * 엔티티의 식별자는 고유성, 불변성을 모두 만족시켜야 한다.
     * email도 좋은 식별자가 될 수 있지만 추후 이메일 변경드을 통해 불변성이 깨질 위험이 있다.
     * 따라서 식별자는 비즈니스적인 의미에서 완전히 벗어난 인조 키를 사용하는 것이 좋다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @NaturalId
    private Email email;

    private String nickname;

    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;



    public static Member register(MemberRegisterRequest memberRegisterRequest, PasswordEncoder passwordEncoder){
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
