package com.bank.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.models.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}
