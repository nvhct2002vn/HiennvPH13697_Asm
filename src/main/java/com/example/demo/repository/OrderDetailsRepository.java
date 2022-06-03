package com.example.demo.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {

	@Query("SELECT c FROM order_details c Where order_id= :order")
	public List<OrderDetail> getAllByIDCart(int order);

}
