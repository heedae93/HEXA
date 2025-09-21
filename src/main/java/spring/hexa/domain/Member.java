package spring.hexa.domain;

import lombok.Getter;

@Getter
public class Member {
//    회원 Entity의 속성 정의
    String email;
    String nickName;
    String passwordHash;
    MemberStatus status;

    public Member(String email, String nickName, String passwordHash) {
        this.email = email;
        this.nickName = nickName;
        this.passwordHash = passwordHash;
        this.status = MemberStatus.PENDING;
    }
}
