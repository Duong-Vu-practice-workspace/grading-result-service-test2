package com.ptit.grading.result;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.ptit.grading.common.model", "com.ptit.grading.result.model"})
public class ResultServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultServiceApplication.class, args);
    }
}
