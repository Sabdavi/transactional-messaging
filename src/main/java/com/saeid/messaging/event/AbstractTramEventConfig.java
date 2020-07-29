package com.saeid.messaging.event;


import com.saeid.messaging.event.domain.Account;

public class AbstractTramEventConfig {

  private long uniqueId = System.currentTimeMillis();
  private String  aggregateType = Account.class.getName() + uniqueId;
  private String aggregateId = "accountId" + uniqueId;

  public long getUniqueId() {
    return uniqueId;
  }

  public String getAggregateType() {
    return aggregateType;
  }

  public String getAggregateId() {
    return aggregateId;
  }
}
