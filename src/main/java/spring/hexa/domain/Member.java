package spring.hexa.domain;

import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Getter
public class Member {
//    회원 Entity의 속성 정의
    String email;
    String nickname;
    String passwordHash;
    MemberStatus status;

    private Member( ) {
    }

     static Member create (MemberCreateRequest memberCreateRequest, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.email = requireNonNull(memberCreateRequest.email());
        member.nickname = requireNonNull(memberCreateRequest.nickname());
        member.passwordHash = requireNonNull(passwordEncoder.encode(memberCreateRequest.password()));
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
