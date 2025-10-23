package spring.hexa.adapter.security;

import org.springframework.stereotype.Component;
import spring.hexa.domain.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component  // ✅ Adapter layer에서도 Bean 등록 가능
public class Sha256PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    @Override
    public boolean matches(String password, String passwordHash) {
        return encode(password).equals(passwordHash);
    }
}
