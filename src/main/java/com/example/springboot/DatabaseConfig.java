package com.example.springboot;

import com.example.springboot.dal.CartDatabase;
import com.example.springboot.dal.CustomerProfile;
import com.example.springboot.dal.InventoryDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DatabaseConfig {

  @Bean
  @Scope("singleton")
  public CartDatabase cartDatabase() {
    return new CartDatabase();
  }

  @Bean
  @Scope("singleton")
  public CustomerProfile customerProfile() {
    return new CustomerProfile();
  }

  @Bean
  @Scope("singleton")
  public InventoryDatabase inventoryDatabase() {
    return new InventoryDatabase();
  }

}
