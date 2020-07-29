package com.saeid.messaging.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JdbcKafkaTramEventConfiguration.class)
public class EventMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventMessagingApplication.class, args);
    }


}
