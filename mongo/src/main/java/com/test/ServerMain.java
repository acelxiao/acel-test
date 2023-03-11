package com.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ServerMain  {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServerMain.class);
        app.run(args);
        System.out.println("=============");
        try {
            Thread.sleep(99999999L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
