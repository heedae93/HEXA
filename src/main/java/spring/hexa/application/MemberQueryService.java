package spring.hexa.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import spring.hexa.application.provided.MemberFinder;
import spring.hexa.application.required.MemberRepository;
import spring.hexa.domain.Member;


@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class MemberQueryService implements MemberFinder {
    private final MemberRepository memberRepository;

    @Override
    public Member find(Long memberId) {
        return  memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException(memberId + "로 회원을 찾을 수 없습니다."));

    }


}
