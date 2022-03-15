package com.deepinsea.springbootscheduletask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootScheduletaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootScheduletaskApplication.class, args);
    }

}
