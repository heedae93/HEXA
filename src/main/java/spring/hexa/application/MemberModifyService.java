package spring.hexa.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import spring.hexa.application.provided.MemberFinder;
import spring.hexa.application.provided.MemberRegister;
import spring.hexa.application.required.EmailSender;
import spring.hexa.application.required.MemberRepository;
import spring.hexa.domain.*;


@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class MemberModifyService implements MemberRegister {

    private final MemberFinder memberFinder;
    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member register( MemberRegisterRequest memberRegisterRequest) {

        checkDuplicateEmail(memberRegisterRequest);

        Member member = Member.register(memberRegisterRequest, passwordEncoder);

        memberRepository.save(member);

        sendWelcomeEmail(member);

        return member;
    }

    @Override
    public Member activate(Long memberId) {
        Member member = memberFinder.find(memberId);

        member.activate();

        return memberRepository.save(member);
    }

    private void sendWelcomeEmail(Member member) {
        emailSender.send(member.getEmail(),"등록을 완료해주세요","아래 링크를 클릭해서 등록을 완료 해주세요");
    }

    private void checkDuplicateEmail(MemberRegisterRequest memberRegisterRequest) {
        if (memberRepository.findByEmail(new Email(memberRegisterRequest.email())).isPresent()){
            throw new DuplicateEmailException("이미 사용중인 이메일입니다 = " + memberRegisterRequest.email());
        }
    }


}
