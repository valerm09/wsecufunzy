package com.example.springboot;

public class Item {
  public Item(Product type, long cost) {
    this.productType = type;
    this.productId = type.ordinal();
    this.cost = cost;
  }
  public enum Product {
    SODA,
    CANDYBAR,
    CHIPS
  }
  public int productId;
  public Product productType;
  public long cost;
}
