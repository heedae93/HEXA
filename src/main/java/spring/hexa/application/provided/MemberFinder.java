package spring.hexa.application.provided;

import spring.hexa.domain.Member;

public interface MemberFinder {
    Member find(Long memberId);
}
