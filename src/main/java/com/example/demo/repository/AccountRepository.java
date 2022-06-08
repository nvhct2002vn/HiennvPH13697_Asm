package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
//	@Query("SELECT acc FROM Account acc WHERE acc.username LIKE :username")
//	public Account findByUsername(String username);
	
	@Query("SELECT acc FROM Account acc WHERE acc.email LIKE :email")
	public Account findByEmail(String email);
}
