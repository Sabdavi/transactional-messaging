package com.saeid.messaging.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JdbcKafkaTramCommandConfiguration.class)
public class CommandMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommandMessagingApplication.class, args);
    }


}
