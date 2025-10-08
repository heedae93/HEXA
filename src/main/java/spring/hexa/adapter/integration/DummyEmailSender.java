package spring.hexa.adapter.integration;

import org.springframework.stereotype.Component;
import spring.hexa.application.required.EmailSender;
import spring.hexa.domain.Email;

@Component
public class DummyEmailSender implements EmailSender {
    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("DummyEmailSender = " + email);
    }
}
