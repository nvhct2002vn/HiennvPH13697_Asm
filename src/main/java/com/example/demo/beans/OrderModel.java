package com.example.demo.beans;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@Size(max = 255, message = "Địa chỉ không điền quá 255 ký tự")
	private String address;
	@NotBlank(message = "Vui lòng không để trống số điện thoại")
	@Size(max = 10,min = 10, message = "Số điện thoại phải là 10 ký tự")
	private String sdt;
	private int status;
}
