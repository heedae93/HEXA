package spring.hexa.application.required;


import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import spring.hexa.domain.Member;
import spring.hexa.domain.MemberFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static spring.hexa.domain.MemberFixture.createMemberRegisterRequest;
import static spring.hexa.domain.MemberFixture.createPasswordEncoder;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void createMember () {
        Member member = Member.create(createMemberRegisterRequest(), createPasswordEncoder());

        assertThat(member.getId()).isNull();
        memberRepository.save(member);
        assertThat(member.getId()).isNotNull();
        entityManager.flush();
    }

    @Test
    void duplicateEmailCheckFail () {
        Member member = Member.create(createMemberRegisterRequest(),createPasswordEncoder());
        memberRepository.save(member);

        Member member2 = Member.create(createMemberRegisterRequest(),createPasswordEncoder());
        assertThatThrownBy(() -> memberRepository.save(member2))
                .isInstanceOf(DataIntegrityViolationException.class);
    }



}