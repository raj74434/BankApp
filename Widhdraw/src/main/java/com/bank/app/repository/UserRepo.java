package com.bank.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.models.Account;
import com.bank.app.models.Users;

public interface UserRepo extends JpaRepository<Users, Integer>{

}
