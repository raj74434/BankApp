package com.bank.app.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Account {
@Id
	private Integer account_id;
	private Integer balance;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "account")
	List<Users> users=new ArrayList<>();

	public Integer getAccount_id() {
		return account_id;
	}

	@Override
	public String toString() {
		return "Account{" +
				"account_id=" + account_id +
				", balance=" + balance +
				'}';
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
}
