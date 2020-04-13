package com.server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com"})

public class ProjetoFinalApplication {

    public static void main(String[] args) {

    SpringApplication.run(ProjetoFinalApplication.class, args);

    }

}