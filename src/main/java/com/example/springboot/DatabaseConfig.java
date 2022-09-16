package com.example.springboot;

import java.util.HashMap;
import java.util.Map;
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

}
