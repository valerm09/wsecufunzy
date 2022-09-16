package com.example.springboot;

public class LedgerEntry {
  public final String transactionId; // Will be composite of items.
  public final String details;
  public final long amountDelta;
  public LedgerEntry(String id, long delta, String details) {
    this.transactionId = id;
    this.amountDelta = delta;
    this.details = details;
  }
}
