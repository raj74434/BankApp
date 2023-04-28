package com.bank.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.app.models.Account;
import com.bank.app.models.Users;
import com.bank.app.repository.AccountRepo;
import com.bank.app.repository.UserRepo;

@Service
public class DebitServiceImpl implements DebitService {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private UserRepo userRepo;


	@Override
	public String debit(Integer user_id, Integer amount) {

		try {
			Users user = findUser(user_id);
			Account account = user.getAccount();
			Integer totalAmount = account.getBalance();
			Integer finalAmount = totalAmount - amount;
			if (finalAmount >= 0) {
				setBalance(finalAmount, user);
				return account.getBalance() + "";
			} else {
				return "Balance is low";
			}

		} catch (Exception e) {

			return "Unable to find User with this Id";
//			e.printStackTrace();
		}


	}

	@Override
	public Users findUser(Integer id) throws Exception {
		Optional<Users> user = userRepo.findById(id);
		if (user.isEmpty()) throw new Exception("Unable to find user with this id");
		return user.get();
	}

	@Override
	public Account setBalance(Integer amount, Users users) {

		Account account = users.getAccount();
		account.setBalance(amount);
		accountRepo.save(account);
		return account;
	}

	@Override
	public Account createAccount(Account acc) {
		List<Users> lst = acc.getUsers();
		for (int x = 0; x < lst.size(); x++) {
			lst.get(x).setAccount(acc);
			System.out.println(lst.get(x).getName());
		}

		return accountRepo.save(acc);
	}


	public Account credit(Integer user_id, Integer amount) throws Exception {
		Users user = findUser(user_id);
		if (user != null) {
			Account acc = user.getAccount();
			Integer finalAmount = acc.getBalance() + Math.abs(amount);
			if (finalAmount <= 10000000) {
				return setBalance(finalAmount, user);

			} else {
				throw new Exception("Unable to deposite");
			}
		}
		else throw new Exception("No user found");
	}


}
