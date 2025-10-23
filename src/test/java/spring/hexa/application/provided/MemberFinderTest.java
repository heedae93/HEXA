package spring.hexa.application.provided;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.hexa.domain.Member;
import spring.hexa.domain.MemberFixture;

import javax.swing.text.html.parser.Entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Import(MemberTestConfiguration.class)
class MemberFinderTest {

    @Autowired
    MemberRegister memberRegister;
    @Autowired
    MemberFinder memberFinder;
    @Autowired
    EntityManager entityManager;

    @Test
    void find () {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());
        entityManager.flush();
        entityManager.clear();

        Member found = memberFinder.find(member.getId());

        assertThat(member.getId()).isEqualTo(found.getId());
    }

}