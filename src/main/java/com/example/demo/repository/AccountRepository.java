package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
