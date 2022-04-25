package com.example.Vteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VTeam {

    public static void main(String[] args) {
        SpringApplication.run(VTeam.class, args);
    }

}
