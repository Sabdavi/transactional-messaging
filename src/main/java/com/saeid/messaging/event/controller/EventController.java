package com.saeid.messaging.event.controller;

import com.saeid.messaging.event.AbstractTramEventConfig;
import com.saeid.messaging.event.TramEventEventConsumer;
import com.saeid.messaging.event.domain.AccountDebited;
import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@RestController
public class EventController {
    private final DomainEventPublisher domainEventPublisher;
    private final AbstractTramEventConfig config;
    private final TramEventEventConsumer tramEventEventConsumer;

    public EventController(DomainEventPublisher domainEventPublisher, AbstractTramEventConfig config, TramEventEventConsumer tramEventEventConsumer) {
        this.domainEventPublisher = domainEventPublisher;
        this.config = config;
        this.tramEventEventConsumer = tramEventEventConsumer;
    }

    @RequestMapping("/event")
    public String shouldReceiveMessage() throws InterruptedException {

        long uniqueId = config.getUniqueId();

        DomainEvent domainEvent = new AccountDebited(uniqueId);

        domainEventPublisher.publish(config.getAggregateType(), config.getAggregateId(), Collections.singletonList(domainEvent));


        AccountDebited event = tramEventEventConsumer.getQueue().poll(100, TimeUnit.SECONDS);

        return event.toString();

    }
}
