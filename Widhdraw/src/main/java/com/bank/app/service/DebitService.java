package com.bank.app.service;

import com.bank.app.models.Account;
import com.bank.app.models.Users;

public interface DebitService {
	public Users findUser(Integer id) throws Exception;
	public String debit(Integer user_id,Integer amount);
	Account setBalance(Integer amount, Users users);
	Account createAccount(Account acc);
	public Account credit(Integer user_id,Integer amount) throws Exception ;
}
