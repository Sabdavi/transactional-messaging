package com.saeid.messaging.command;

import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.commands.consumer.CommandDispatcherFactory;
import io.eventuate.tram.spring.commands.consumer.TramCommandConsumerConfiguration;
import io.eventuate.tram.spring.commands.producer.TramCommandProducerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TramCommandProducerConfiguration.class, TramCommandConsumerConfiguration.class})
public class AbstractTramCommandConfiguration {

  @Bean
  public AbstractTramCommandConfig abstractTramCommandTestConfig() {
    return  new AbstractTramCommandConfig();

  }

  @Bean
  public TramCommandCommandHandler abstractTramCommandTestTarget(AbstractTramCommandConfig config) {
    return new TramCommandCommandHandler(config.getCommandChannel());
  }

  @Bean
  public CommandDispatcher commandDispatcher(CommandDispatcherFactory commandDispatcherFactory,
                                             AbstractTramCommandConfig config, TramCommandCommandHandler target) {
    return commandDispatcherFactory.make(config.getCommandDispatcherId(), target.getCommandHandlers());
  }

}
