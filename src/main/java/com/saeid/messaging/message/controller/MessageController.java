package com.saeid.messaging.message.controller;

import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.producer.MessageBuilder;
import io.eventuate.tram.messaging.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@RestController
public class MessageController {

    private long uniqueId = System.currentTimeMillis();

    private String subscriberId = "subscriberId" + uniqueId;
    private String destination = "destination" + uniqueId;
    private String payload = "Hello" + uniqueId;

    private final MessageProducer messageProducer;
    private final MessageConsumer messageConsumer;

    private BlockingQueue<Message> queue = new LinkedBlockingDeque<>();

    public MessageController(MessageProducer messageProducer, MessageConsumer messageConsumer) {
        this.messageProducer = messageProducer;
        this.messageConsumer = messageConsumer;
    }

    @RequestMapping("/message")
    public String shouldReceiveMessage() throws InterruptedException {
        messageConsumer.subscribe(subscriberId, Collections.singleton(destination), this::handleMessage);
        messageProducer.send(destination, MessageBuilder.withPayload(payload).build());

        Message m = queue.poll(10, TimeUnit.SECONDS);
        return m.toString();
    }

    private void handleMessage(Message message) {
        queue.add(message);
    }
}
