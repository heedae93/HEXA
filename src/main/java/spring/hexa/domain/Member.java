package spring.hexa.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Member {
//    회원 Entity의 속성 정의
    String email;
    String nickName;
    String passwordHash;
    MemberStatus status;

    public Member(String email, String nickName, String passwordHash) {
        this.email = Objects.requireNonNull(email);
        this.nickName = Objects.requireNonNull(nickName);
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.status = MemberStatus.PENDING;
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
}
