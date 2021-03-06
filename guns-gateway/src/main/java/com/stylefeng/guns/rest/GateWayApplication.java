package com.stylefeng.guns.rest;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDubboConfiguration
@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.stylefeng.guns"})
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableHystrix
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
