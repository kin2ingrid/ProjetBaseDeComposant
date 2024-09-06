package bf.ibam.masterinformatique.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/actuator/**").permitAll() // Utilise requestMatchers au lieu de antMatchers
                    .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

}
