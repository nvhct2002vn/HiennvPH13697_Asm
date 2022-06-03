package com.example.demo.beans;

import com.example.demo.entities.Account;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsModel {
	private Product product;
	private Order order;
	private Account account;
}
