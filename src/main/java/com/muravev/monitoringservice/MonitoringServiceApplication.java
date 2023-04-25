package com.muravev.monitoringservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class MonitoringServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringServiceApplication.class, args);
    }

}
