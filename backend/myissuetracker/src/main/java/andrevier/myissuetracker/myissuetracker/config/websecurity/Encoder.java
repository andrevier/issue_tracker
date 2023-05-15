package andrevier.myissuetracker.myissuetracker.config.websecurity;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("Compare: " + encodedPassword + " with " + rawPassword);
        return encodedPassword.equals(rawPassword.toString());
    }
    
}
