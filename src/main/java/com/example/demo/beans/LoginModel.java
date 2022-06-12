package com.example.demo.beans;

import javax.validation.constraints.NotBlank;

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
	private String username;
	@NotBlank(message = "Vui lòng nhập password.")
	private String password;
}
