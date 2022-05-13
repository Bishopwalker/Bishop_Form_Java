package com.example.bishops.form.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User victoria = new User(
                    "Victoria",
                    "Johnson",
                    "tory@hon.com",
                    33,
                    "working and raising her kids");

            User david = new User(
                    "David",
                    "Jay",
                    "email@mail.com",
                    40,
                    "working and raising his kids");

            repository.saveAll(List.of(victoria, david));
        };
    }
}
