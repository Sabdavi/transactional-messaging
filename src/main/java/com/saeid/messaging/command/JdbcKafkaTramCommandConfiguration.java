package com.saeid.messaging.command;

import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({AbstractTramCommandConfiguration.class, TramJdbcKafkaConfiguration.class, })
public class JdbcKafkaTramCommandConfiguration {
}
