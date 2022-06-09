package com.example.demo.beans;

import java.io.Serializable;

import com.example.demo.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel implements Serializable{
	private Product product;
	private int quantity;
}
