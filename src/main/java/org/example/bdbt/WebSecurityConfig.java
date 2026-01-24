package org.example.bdbt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // 1. ODBLOKOWANIE REJESTRACJI: Dodano "/register" i "/register_action"
                        .requestMatchers("/", "/index", "/login", "/register", "/register_action").permitAll()
                        .requestMatchers("/static/**", "/css/**", "/js/**", "/assets/**", "/webjars/**").permitAll()

                        // Uprawnienia dla ADMINA
                        .requestMatchers("/admin_main", "/all_orders").hasRole("ADMIN")
                        .requestMatchers("/save", "/update", "/delete/**", "/delete_ladunek_admin/**").hasRole("ADMIN")

                        // Uprawnienia dla USERA
                        .requestMatchers("/user_main", "/user_orders", "/save_ladunek", "/delete_ladunek/**").hasRole("USER")

                        // Reszta wymaga logowania
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/perspectives", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/index")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ZMIANA: Zamiast InMemory, używamy bazy danych
    @Bean
    public UserDetailsManager userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        // Zapytanie pobierające użytkownika (Login, Hasło, czyAktywny)
        manager.setUsersByUsernameQuery(
                "SELECT \"Login\", \"Haslo\", 1 FROM \"Klienci\" WHERE \"Login\" = ?"
        );

        // Zapytanie przydzielające role
        // Admin dostaje ROLE_ADMIN, każdy inny (w tym nowi rejestrowani) dostaje ROLE_USER
        manager.setAuthoritiesByUsernameQuery(
                "SELECT \"Login\", CASE WHEN \"Login\" = 'admin' THEN 'ROLE_ADMIN' ELSE 'ROLE_USER' END FROM \"Klienci\" WHERE \"Login\" = ?"
        );

        return manager;
    }
}