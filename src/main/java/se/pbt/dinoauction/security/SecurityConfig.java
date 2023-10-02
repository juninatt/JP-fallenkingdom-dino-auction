package se.pbt.dinoauction.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/**
 * Configuration class for web security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines security rules for HTTP requests.
     *
     * @param http The HttpSecurity object.
     * @return The SecurityFilterChain.
     * @throws Exception Any exception that may occur during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(antMatcher(HttpMethod.POST)).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.PUT)).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.DELETE)).hasRole("ADMIN")
                         .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("/gui/index/index.html", true)
                        .permitAll());

        return http.build();
    }

    /**
     * Provides a password encoder bean.
     *
     * @return A BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
