package com.saeid.messaging.message;

import com.saeid.messaging.event.JdbcKafkaTramEventConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JdbcKafkaTramEventConfiguration.class)
public class MessageMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageMessagingApplication.class, args);
    }


}
