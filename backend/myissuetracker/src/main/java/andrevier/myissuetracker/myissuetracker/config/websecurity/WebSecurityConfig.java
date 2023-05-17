package andrevier.myissuetracker.myissuetracker.config.websecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) 
        throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .anyRequest().authenticated()
        )
        .formLogin(formLogin -> formLogin.permitAll())
        .httpBasic(Customizer.withDefaults());
        
        // Enable csrf in production.
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    public Encoder passwordEncoder() {
        return new Encoder();
    }
}
