package com.bank.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String debit(@PathVariable("user_id") Integer user_id,
			@PathVariable("amount")Integer amount) {
		return debitService.debit(user_id, amount);
	}
	
	@GetMapping("/debit/findUser/{userId}")
	public Users findUser(@PathVariable("userId") Integer id) throws Exception {
		Users user=debitService.findUser(id);
				System.out.println(user);
		return user ;
	}
	
	@GetMapping("/debit/setBalance/{userId}/{amount}")
	public Account setBalance(@PathVariable("userId") Integer id,
			@PathVariable("amount") Integer amount) throws Exception {
		Users user=debitService.findUser(id);
		return debitService.setBalance(amount,user);
	}
	
	@PostMapping("/create")
	public Account createAccount(@RequestBody  Account acc) {
		return debitService.createAccount(acc);
	}



//	---------------------------------

	@PostMapping("/credit/{userid}/{amount}")
	public void credit(@PathVariable("userid") Integer user_id,
					   @PathVariable("amount")Integer amount) throws Exception {
		debitService.credit(user_id, amount);
	}

}
