package spring.hexa.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test
    void createMember () {
        Member member = new Member("hdh@test.com", "hdh","secret");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void nullCheck () {
        assertThatThrownBy(() -> new Member(null,"hdh","secret"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void activate () {
        Member member = new Member("hdh@test.com","hdh","secret");
        member.activate();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void reactivate () {
        Member member = new Member("hdh@test.com","hdh","secret");
        member.activate();
        assertThatThrownBy(() -> {
            member.activate();
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate () {
        Member member = new Member("hdh@test.com","hdh","secret");
        member.activate();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATE);
    }
}