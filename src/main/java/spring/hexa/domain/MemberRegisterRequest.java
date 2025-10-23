package spring.hexa.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record MemberRegisterRequest(
        @Email String email,
        @Size(min = 1, max = 20) String nickname,
        @Size(min = 1 , max = 100) String password) {


}
