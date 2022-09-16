package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

	@Autowired
	CartDatabase cart;

	@GetMapping("cart/")
	public String getCart() {
		return cart.getCartTotal()+"";
	}

	@PostMapping("cart/product")
	public void add(@RequestParam int productId) {
		cart.addItemToCart(productId);
	}

	@DeleteMapping("cart/product")
	public void delete(@RequestParam int productId) {
		cart.removeItemFromCart(productId);
	}
}
