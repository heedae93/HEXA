package spring.hexa.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp () {
       this.passwordEncoder =  new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };


        member = Member.create(new MemberCreateRequest("hdh@test.com","hdh","secret"), passwordEncoder );
    }

    @Test
    void createMember () {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void nullCheck () {
        assertThatThrownBy(() -> member.create(new MemberCreateRequest(null,"hdh","secret"), new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return "";
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return false;
            }
        }))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void activate () {

        member.activate();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void reactivate () {

        member.activate();
        assertThatThrownBy(() -> {
            member.activate();
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate () {

        member.activate();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATE);
    }

    @Test
    void veryfiPassword () {
        assertThat(member.verifyPassword("secret",passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("secret2",passwordEncoder)).isFalse();
    }

    @Test
    void changeNickname () {
        member.changeNickname("ronaldo");
        assertThat(member.getNickname()).isEqualTo("ronaldo");
    }

    @Test
    void changePassword() {
        member.changePassword("secret3",passwordEncoder);

        assertThat(member.getPasswordHash()).isEqualTo(passwordEncoder.encode("secret3"));
    }

    @Test
    void isActive () {
        assertThat(member.isActive()).isFalse();

        member.activate();
        assertThat(member.isActive()).isTrue();

        member.deactivate();
        assertThat(member.isActive()).isFalse();
    }
}