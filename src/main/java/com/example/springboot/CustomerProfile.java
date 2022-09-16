package com.example.springboot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

  public void adjustCredit(String id, String reason, long delta) {
    // Always adding to ledger; immutable.
    transactions.add(new LedgerEntry(id, delta, reason));
  }

}
