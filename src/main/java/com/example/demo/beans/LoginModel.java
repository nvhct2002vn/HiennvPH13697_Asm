package com.example.demo.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel {
//	@NotBlank(message = "Vui lòng nhập username.")
//	private String username;
	@NotBlank(message = "Vui lòng nhập username.")
	@Size(max = 255, message = "Username không điền quá 255 ký tự")
	private String username;
	@NotBlank(message = "Vui lòng nhập password.")
	@Size(max = 255, message = "Password không điền quá 255 ký tự")
	private String password;
}
