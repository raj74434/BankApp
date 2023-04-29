package com.bank.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.app.models.Account;
import com.bank.app.models.Users;

@Service
public class DepositServiceImpl implements DepositService {

	@Autowired
	private RestTemplate restTemplate;
	
	public Account credit(Integer user_id,Integer amount) throws Exception {
	Users user =restTemplate.getForObject("http://localhost:9002/debit/findUser/"+user_id, Users.class);
	System.out.println(user);
	if(user !=null) {
		;
		Account acc = user.getAccount();
		System.out.println(acc);
		System.out.println(acc.getBalance());

		Integer finalAmount = acc.getBalance() + amount;
		if (finalAmount <= 10000000) {
			return restTemplate.getForObject("http://localhost:9002/debit/setBalance/" + user_id + "/" + finalAmount, Account.class);

		} else {
			throw new Exception("Unable to deposite");
		}
	}
	else throw new Exception("Unable to find user");

//	return null;
	}
}
