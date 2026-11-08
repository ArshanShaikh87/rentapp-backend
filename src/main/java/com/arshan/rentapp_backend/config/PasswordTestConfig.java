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
           System.out.print("Generated Password Hash :");
    };
    }
}
