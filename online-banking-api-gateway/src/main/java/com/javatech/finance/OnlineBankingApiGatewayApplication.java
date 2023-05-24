package com.javatech.finance;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OnlineBankingApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineBankingApiGatewayApplication.class, args);

        //spring cloud circuit broker in freight client config+, when we sent request, 2nd time we shut down application
    }


}
