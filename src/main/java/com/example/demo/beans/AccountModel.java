package com.example.demo.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
	private int id;
	@NotBlank(message = "Vui lòng nhập họ tên")
	private String fullname;
	@Email(message = "Không đúng định dạng Email")
	@NotEmpty(message = "Vui lòng nhập email")
	private String email;
	@NotBlank(message = "Vui lòng nhập username")
	private String username;
	@NotBlank(message = "Vui lòng nhập password")
	private String password;
	private String photo;
//	@NotEmpty(message = "Vui lòng chọn photo")
	private MultipartFile multiImage;
	private int activated;
	private int admin;
}
