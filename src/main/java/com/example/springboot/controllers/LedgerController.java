package com.example.springboot.controllers;

import com.example.springboot.dal.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LedgerController {

	@Autowired
	private CustomerProfile customer;

	@GetMapping("ledger/credit")
	public String index() {
		return customer.getCustomerCredit() + "";
	}

	@GetMapping("ledger/")
	public String viewEntireLedger() {
		// Hand-wavy tostring, should use gson.
		return customer.viewLedger().toString();
	}

	@DeleteMapping("ledger/")
	public void refund(@RequestParam String transactionId) {
		customer.refundTransaction(transactionId);
	}
}
