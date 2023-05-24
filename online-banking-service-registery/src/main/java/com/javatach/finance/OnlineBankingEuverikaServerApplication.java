package com.javatach.finance;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class OnlineBankingEuverikaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineBankingEuverikaServerApplication.class, args);
    }
}
