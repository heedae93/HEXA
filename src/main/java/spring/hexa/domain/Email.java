package spring.hexa.domain;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

@Embeddable
public record Email(String email) {

    private static Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    public Email {

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다: " + email);
        }
    }
}
