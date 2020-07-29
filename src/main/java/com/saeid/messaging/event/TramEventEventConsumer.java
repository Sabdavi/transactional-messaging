package com.saeid.messaging.event;


import com.saeid.messaging.event.domain.AccountDebited;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TramEventEventConsumer {

  private BlockingQueue<AccountDebited> queue = new LinkedBlockingDeque<>();
  private String aggregateType;

  public TramEventEventConsumer(String aggregateType) {
    this.aggregateType = aggregateType;
  }

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder
            .forAggregateType(aggregateType)
            .onEvent(AccountDebited.class, this::handleAccountDebited)
            .build();
  }

  public void handleAccountDebited(DomainEventEnvelope<AccountDebited> event) {
    queue.add(event.getEvent());
  }

  public BlockingQueue<AccountDebited> getQueue() {
    return queue;
  }
}
