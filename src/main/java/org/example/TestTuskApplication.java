package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
public class TestTuskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTuskApplication.class, args);
    }
}