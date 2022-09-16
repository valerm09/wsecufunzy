package com.example.springboot;

import com.example.springboot.Item.Product;
import java.util.HashMap;
import java.util.Map;

public class InventoryDatabase {
  private Map<Integer, Integer> productIdToInventoryMap;
  final int SODAID = 0;
  final int CANDYBARID = 1;
  final int CHIPSID = 2;
  public InventoryDatabase() {
    productIdToInventoryMap = new HashMap<>();

    // Init starting inventories
    productIdToInventoryMap.put(SODAID, 1);
    productIdToInventoryMap.put(CANDYBARID, 2);
    productIdToInventoryMap.put(CHIPSID, 3);
  }

  public Item getItemByProductId(int productId) {
    switch(productId) {
      case SODAID:
          return new Item(Product.SODA, 95);
      case CANDYBARID:
        return new Item(Product.CANDYBAR, 60);
      case CHIPSID:
        return new Item(Product.CHIPS, 99);
      default:
        throw new RuntimeException("Product id not found");
    }
  }
  public void addInventory(Item item) {
    productIdToInventoryMap.put(item.productId, productIdToInventoryMap.get(item.productId) + 1);
  }

  public void removeInventory(Item item) {
    if(productIdToInventoryMap.get(item.productId) == 0) {
      throw new RuntimeException("Cannot set negative inventory");
    }
    productIdToInventoryMap.put(item.productId, productIdToInventoryMap.get(item.productId) - 1);
  }

  public boolean itemHasInventory(Item item) {
    return productIdToInventoryMap.get(item.productId) != 0;
  }
}
