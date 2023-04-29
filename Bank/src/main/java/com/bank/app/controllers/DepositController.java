package com.bank.app.controllers;

import java.nio.file.Path;

import com.bank.app.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.bank.app.service.DepositService;

@Controller
public class DepositController {

	@Autowired
	private DepositService depositService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/credit/{userid}/{amount}")
     public ResponseEntity<String> credit(@PathVariable("userid") Integer user_id,
										  @PathVariable("amount")Integer amount) throws Exception {
		Account account=depositService.credit(user_id, amount);
		return new ResponseEntity<>("Deposite Sucess final balance  is "+account.getBalance(), HttpStatus.ACCEPTED);
	}
	
	
}
