package spring.hexa.application.provided;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import spring.hexa.application.required.EmailSender;
import spring.hexa.domain.MemberFixture;
import spring.hexa.domain.PasswordEncoder;

@TestConfiguration
class MemberTestConfiguration {

    @Bean
    public EmailSender emailSender() {
        return (email, subject, body) -> System.out.println("Sending Email" + email);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return MemberFixture.createPasswordEncoder();
    }
}
