package com.example.springboot.dal;

import java.util.LinkedList;
import java.util.List;
import org.springframework.boot.actuate.endpoint.web.Link;

public class CustomerProfile {
  private List<LedgerEntry> transactions;
  public CustomerProfile() {
    final long TWO_DOLLARS = 200;
    transactions = new LinkedList<>();
    transactions.add(new LedgerEntry("STARTING", TWO_DOLLARS, "STARTING MONEY"));
  }

  public long getCustomerCredit() {
    long sum = 0;
    for (LedgerEntry e : transactions) {
      sum += e.amountDelta;
    }
    return sum;
  }

  public List<LedgerEntry> viewLedger() {
    // TODO: Verify if this is sufficient shallow copy, may also want to detach
    // item-references too, but maybe doesn't matter since they're FINAL properties.
    return new LinkedList<>(transactions);
  }

  public void adjustCredit(String id, String reason, long delta) {
    // Always adding to ledger; immutable.
    transactions.add(new LedgerEntry(id, delta, reason));
  }

  public void refundTransaction(String transactionId) {
    LedgerEntry entry = null;
    for (LedgerEntry e : this.transactions) {
      if(e.transactionId.equals(transactionId)) {
        entry = e;
      }
    }

    if (entry == null ) {
      throw new RuntimeException("Transaction not found");
    }

    adjustCredit(transactionId, "REVERSAL", entry.amountDelta * - 1);
  }

}
