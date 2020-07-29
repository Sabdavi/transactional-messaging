package com.saeid.messaging.event;

import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({AbstractTramEventConfiguration.class, TramJdbcKafkaConfiguration.class})
public class JdbcKafkaTramEventConfiguration {
}
