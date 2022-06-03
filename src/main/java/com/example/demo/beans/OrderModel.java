package com.example.demo.beans;

import java.util.Date;

import com.example.demo.entities.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
	private Account account;
	private Date createDate;
	private String address;
}
