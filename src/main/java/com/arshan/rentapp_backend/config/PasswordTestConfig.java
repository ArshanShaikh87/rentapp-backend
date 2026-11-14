package com.arshan.rentapp_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordTestConfig {

    @Bean
    public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder) {
       return args -> {
           String rawPassword = "Rent@12345"; // Yahan apna password likhein
           String encodedPassword = passwordEncoder.encode(rawPassword);
           System.out.println("Generated Password Hash : " + encodedPassword);

    };
    }
}
