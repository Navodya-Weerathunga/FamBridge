package com.edu.famBridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration

public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/famBridge/midwife/**").permitAll()
                        .requestMatchers("/famBridge/marriedCoupleRequest/**").permitAll()
                        .requestMatchers("/famBridge/marriedCouple/**").permitAll()
                        .requestMatchers("/famBridge/pregnantWomenRequest/**").permitAll()
                        .requestMatchers("/famBridge/pregnantWomen/**").permitAll()
                        .requestMatchers("/famBridge/CancellationMessage/**").permitAll()
                        .requestMatchers("/famBridge/MidwifeManualMeetingCreate/**").permitAll()
                        .requestMatchers("/famBridge/MidwifeSchedule/**").permitAll()
                        .requestMatchers("/famBridge/VisitChanneling/**").permitAll()
                        .requestMatchers("/famBridge/VisitReminder/**").permitAll()
                        .requestMatchers("/famBridge/pregnancyCard").permitAll()
                        .requestMatchers("/famBridge/pregnancyCard/**").permitAll()
                        .requestMatchers("/api/zoom/**").permitAll()
                        .requestMatchers("/api/advice/**").permitAll()
                        .requestMatchers("/api/suggestions/**").permitAll()
                        .requestMatchers("/famBridge/pregnancyRecord").permitAll()

                        .anyRequest().authenticated()
                );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
