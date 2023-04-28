package com.example.theater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class TheaterApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TheaterApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run();
    }

}
