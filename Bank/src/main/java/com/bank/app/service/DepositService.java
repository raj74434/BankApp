package com.bank.app.service;

import com.bank.app.models.Account;

public interface DepositService {

public Account credit(Integer user_id,Integer amount) throws Exception ;
	
}
