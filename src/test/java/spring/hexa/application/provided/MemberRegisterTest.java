package spring.hexa.application.provided;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.hexa.domain.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(MemberTestConfiguration.class)
class MemberRegisterTest {

    @Autowired
    private MemberRegister memberRegister;

    @Test
    void register () {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void duplicateEmailFail () {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());

        assertThatThrownBy(() -> memberRegister.register(MemberFixture.createMemberRegisterRequest()))
                .isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    void memberRegisterRequestFail() {
        MemberRegisterRequest memberRegisterRequest = new MemberRegisterRequest("hdh@testcom","hdh","secret");

        assertThatThrownBy(() -> memberRegister.register(memberRegisterRequest))
                .isInstanceOfAny(ConstraintViolationException.class, IllegalArgumentException.class);
    }

    @Test
    void activate () {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());

        member = memberRegister.activate(member.getId());
    }
}