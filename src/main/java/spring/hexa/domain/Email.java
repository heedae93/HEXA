package spring.hexa.domain;

import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public record Email(String value) {

    private static Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    public Email {

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다: " + value);
        }
    }
}
