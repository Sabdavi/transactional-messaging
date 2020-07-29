package com.saeid.messaging.command;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandHandlersBuilder;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.commands.consumer.PathVariables;
import io.eventuate.tram.messaging.common.Message;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class TramCommandCommandHandler {

  private String commandChannel;

  public TramCommandCommandHandler(String commandChannel) {
   this.commandChannel = commandChannel;
  }

  public Message doSomething(CommandMessage<DoSomethingCommand> cm, PathVariables pvs) {
    System.out.println("customerId=" + pvs.getString("customerId"));
    System.out.println("cm=" + cm);
    return withSuccess();

  }

  public CommandHandlers getCommandHandlers() {
    return CommandHandlersBuilder
            .fromChannel(commandChannel)
            .onMessage(DoSomethingCommand.class, this::doSomething)
            .build();

  }
}
