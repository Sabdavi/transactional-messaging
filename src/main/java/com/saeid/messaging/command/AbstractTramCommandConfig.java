package com.saeid.messaging.command;

public class AbstractTramCommandConfig {
  private long uniqueId = System.currentTimeMillis();

  private String commandChannel = "commandChannel" + uniqueId;
  private String commandDispatcherId = "commandDispatcherId" + uniqueId;
  private String customerChannel = "customerChannel" + uniqueId;
  private String replyChannel = "replyChannel-" + uniqueId;

  public String getCommandChannel() {
    return commandChannel;
  }

  public String getCommandDispatcherId() {
    return commandDispatcherId;
  }

  public String getCustomerChannel() {
    return customerChannel;
  }

  public long getUniqueId() {
    return uniqueId;
  }

  public String getReplyChannel() {
    return replyChannel;
  }
}
