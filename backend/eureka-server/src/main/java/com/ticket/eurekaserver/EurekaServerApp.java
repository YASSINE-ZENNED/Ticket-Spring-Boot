package com.ticket.eurekaserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {


    public static void main(String[] args) {
        log.info("Eureka Server started successfully");

        SpringApplication.run(EurekaServerApp.class, args);
        log.info("Eureka Server started successfully");
    }
}