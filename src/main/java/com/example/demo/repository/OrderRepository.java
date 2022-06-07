package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT c FROM orders c Where user_id = :user")
	public List<Order> getAllByIDUser(int user);
}
