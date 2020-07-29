package com.saeid.messaging.event;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.spring.events.subscriber.TramEventSubscriberConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TramEventsPublisherConfiguration.class, TramEventSubscriberConfiguration.class})
public class AbstractTramEventConfiguration {

  @Bean
  public AbstractTramEventConfig abstractTramEventTestConfig() {
    return new AbstractTramEventConfig();
  }

  @Bean
  public DomainEventDispatcher domainEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
                                                     AbstractTramEventConfig config,
                                                     TramEventEventConsumer target
                                                  ) {
    return domainEventDispatcherFactory.make("eventDispatcherId" + config.getUniqueId(),
            target.domainEventHandlers());
  }

  @Bean
  public TramEventEventConsumer tramEventTestTarget(AbstractTramEventConfig config) {
    return new TramEventEventConsumer(config.getAggregateType());
  }


}
