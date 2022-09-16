package com.example.springboot;

import com.example.springboot.Item.Product;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Cart is comprised OF items.
 */
public class CartDatabase {

  private InventoryDatabase inventory;
  private CustomerProfile customer;
  private Map<Integer, List<Item>> productToQuantityItemsMap;
  public CartDatabase() {
    inventory = new InventoryDatabase();
    customer = new CustomerProfile();
    productToQuantityItemsMap = new HashMap<>();
    productToQuantityItemsMap.put(Product.SODA.ordinal(), new LinkedList<>());
    productToQuantityItemsMap.put(Product.CANDYBAR.ordinal(), new LinkedList<>());
    productToQuantityItemsMap.put(Product.CHIPS.ordinal(), new LinkedList<>());
  }

  private void clearCart() {
    productToQuantityItemsMap.put(Product.SODA.ordinal(), new LinkedList<>());
    productToQuantityItemsMap.put(Product.CANDYBAR.ordinal(), new LinkedList<>());
    productToQuantityItemsMap.put(Product.CHIPS.ordinal(), new LinkedList<>());
  }

  public void addItemToCart(int productId) {
    addItemToCart(inventory.getItemByProductId(productId));
  }

  public void addItemToCart(Item item) {
    decreaseInventory(item);
    addItem(item);
  }

  private void addItem(Item item) {
    productToQuantityItemsMap.get(item.productId).add(item);
  }

  private void removeItem(Item item) {
    productToQuantityItemsMap.get(item.productId).remove(0);
  }

  private void decreaseInventory(Item item) {
    if(!inventory.itemHasInventory(item)) {
      throw new RuntimeException("Exceed max reservable quantity");
    }
    inventory.removeInventory(item);
  }

  public void removeItemFromCart(int productId) {
    removeItemFromCart(inventory.getItemByProductId(productId));
  }
  public void removeItemFromCart(Item item) {
    inventory.addInventory(item);
    removeItem(item);
  }

  public long getCartTotal() {
    long sum = 0;
    for (Map.Entry e : productToQuantityItemsMap.entrySet()) {
      for (Item ei : (List<Item>)e.getValue()) {
        sum += ei.cost;
      }
    }
    return sum;
  }

  public void checkout() {
    if(!canCustomerPurchaseAmount(getCartTotal())) {
      throw new RuntimeException("Cannot exceed credit available for checkout");
    }
    recordCartPurchase();
    clearCart();

  }

  private void recordCartPurchase() {
    customer.adjustCredit(constructHashId(), "CHECKOUT",getCartTotal() * - 1);
  }

  private boolean canCustomerPurchaseAmount(long purchaseAmount) {
    long currentBalance = customer.getCustomerCredit();
    return currentBalance >= purchaseAmount;
  }

  public String constructHashId() {
    StringBuilder idBuilder = new StringBuilder();
    // Instant is timezone agnositc, so no funky EST / CST computer local time issues.
    idBuilder.append("CustomerDefault." + Instant.now().toString()+".");
    for (Map.Entry e : productToQuantityItemsMap.entrySet()) {
      int count = ((List<Item>)e.getValue()).size();
      int productId = (Integer)(e.getKey());
      idBuilder.append("K"+productId+"Q"+count);
    }
    return idBuilder.toString();
  }

}
