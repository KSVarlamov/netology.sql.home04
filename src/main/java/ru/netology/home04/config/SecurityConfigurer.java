package ru.netology.home04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/persons/by-city").permitAll()
                        .requestMatchers("/api/persons/age-less").hasRole("AGE")
                        .requestMatchers("/api/persons/by-fullname").hasRole("NAME")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username("user")
                .password("password")
                .roles("test")
                .build();

        var admin = User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username("admin")
                .password("123")
                .roles("AGE", "NAME")
                .build();

        var moder = User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username("test")
                .password("test")
                .roles("NAME")
                .build();

        var userDetailsManager = new InMemoryUserDetailsManager(user);

        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(moder);

        return userDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
