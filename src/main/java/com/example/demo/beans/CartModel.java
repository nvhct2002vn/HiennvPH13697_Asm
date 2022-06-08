package com.example.demo.beans;

import com.example.demo.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {
	private Product product;
	private int quantity;
}
