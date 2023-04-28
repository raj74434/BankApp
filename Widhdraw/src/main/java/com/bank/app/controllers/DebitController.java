package com.bank.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.app.models.Account;
import com.bank.app.models.Users;
import com.bank.app.service.DebitService;

@Controller
public class DebitController {

	@Autowired
	private DebitService debitService;
	
	@PostMapping("/debit/{user_id}/{amount}")
	public  ResponseEntity<String> debit(@PathVariable("user_id") Integer user_id,
			@PathVariable("amount")Integer amount) {
		return new ResponseEntity<>(debitService.debit(user_id, amount),HttpStatus.OK);
	}
	
	@GetMapping("/debit/findUser/{userId}")
	public  ResponseEntity<Users> findUser(@PathVariable("userId") Integer id) throws Exception {
		Users user=debitService.findUser(id);
				System.out.println(user);
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED) ;
	}
	
	@GetMapping("/debit/setBalance/{userId}/{amount}")
	public ResponseEntity<Account> setBalance(@PathVariable("userId") Integer id,
			@PathVariable("amount") Integer amount) throws Exception {
		Users user=debitService.findUser(id);
		return new ResponseEntity<>(debitService.setBalance(amount,user),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody  Account acc) {
		return new ResponseEntity<>( debitService.createAccount(acc),HttpStatus.CREATED);
	}



//	---------------------------------

	@PostMapping("/credit/{userid}/{amount}")
	public ResponseEntity<String> credit(@PathVariable("userid") Integer user_id,
					   @PathVariable("amount")Integer amount) throws Exception {
		debitService.credit(user_id, amount);
		return new ResponseEntity<>("Amount added in your account",HttpStatus.ACCEPTED);
	}

}
