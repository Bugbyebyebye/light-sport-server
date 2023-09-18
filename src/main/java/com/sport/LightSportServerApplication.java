package com.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication
public class LightSportServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LightSportServerApplication.class, args);
    }

}
