package com.example.demo.beans;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.example.demo.entities.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
	private int id;
	private Account account;
	private Date createDate;
	@NotBlank(message = "Vui lòng không để trống địa chỉ")
	private String address;
	private int status;
}
