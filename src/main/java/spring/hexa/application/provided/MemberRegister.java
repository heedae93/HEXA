package spring.hexa.application.provided;

import spring.hexa.domain.Member;
import spring.hexa.domain.MemberRegisterRequest;

/**
 * 회원 등록과 관련된 기능 제공
 */
public interface MemberRegister {
    Member register(MemberRegisterRequest memberRegisterRequest);
}
