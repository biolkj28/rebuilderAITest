package com.process.enrolment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EnrolmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnrolmentApplication.class, args);
    }

}
