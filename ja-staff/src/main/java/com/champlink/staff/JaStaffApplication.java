package com.champlink.staff;

import com.champlink.common.web.bootstrap.BaseBootstrapApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableJms
@EnableFeignClients(basePackages = {"com.champlink.staff.service", "com.champlink.common.service"})
@EnableScheduling
@ComponentScans({
        @ComponentScan("com.champlink.common.util.cache"),
        @ComponentScan("com.champlink.common.util.file"),
        @ComponentScan("com.champlink.common.util.jms"),
        @ComponentScan("com.champlink.common.util.i18n"),
        @ComponentScan("com.champlink.common.util.context"),
        @ComponentScan("com.champlink.common.advice")})
public class JaStaffApplication extends BaseBootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(JaStaffApplication.class, args);
    }
}
