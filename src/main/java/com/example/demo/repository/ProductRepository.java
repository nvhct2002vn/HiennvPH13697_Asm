package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT c FROM products c Where category_id= :category")
	public List<Product> getAllByIDCate(int category);
}
