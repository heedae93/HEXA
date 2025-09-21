package spring.hexa.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test
    void createMember () {
        Member member = new Member("hdh@test.com", "hdh","secret");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }
}