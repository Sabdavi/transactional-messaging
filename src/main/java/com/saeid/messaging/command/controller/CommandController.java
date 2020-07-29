package com.saeid.messaging.command.controller;


import com.saeid.messaging.command.AbstractTramCommandConfig;
import com.saeid.messaging.command.DoSomethingCommand;
import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@RestController
public class CommandController {


    private final CommandProducer commandProducer;
    private final MessageConsumer messageConsumer;
    private final AbstractTramCommandConfig config;

    private BlockingQueue<Message> queue = new LinkedBlockingDeque<>();

    public CommandController(CommandProducer commandProducer, MessageConsumer messageConsumer, AbstractTramCommandConfig config) {
        this.commandProducer = commandProducer;
        this.messageConsumer = messageConsumer;
        this.config = config;
    }

    @RequestMapping("/command")
    public String getMessage() throws InterruptedException {
        subscribeToReplyChannel();
        sendCommand();
        Message m = queue.poll(10, TimeUnit.SECONDS);
        return m.toString();

    }

    private String sendCommand() {
        return commandProducer.send(config.getCommandChannel(),
                new DoSomethingCommand(),
                config.getReplyChannel(),
                Collections.emptyMap());
    }

    private void subscribeToReplyChannel() {
        String subscriberId = "subscriberId" + config.getUniqueId();
        messageConsumer.subscribe(subscriberId, Collections.singleton(config.getReplyChannel()), this::handleMessage);
    }

    private void handleMessage(Message message) {
        queue.add(message);
    }
}
