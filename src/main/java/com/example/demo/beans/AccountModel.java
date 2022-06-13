package com.example.demo.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	@Size(max = 255, message = "Fullname không điền quá 255 ký tự")
	private String fullname;
	@Email(message = "Không đúng định dạng Email")
	@NotEmpty(message = "Vui lòng nhập email")
	@Size(max = 255, message = "Email không điền quá 255 ký tự")
	private String email;
	@NotBlank(message = "Vui lòng nhập username")
	@Size(max = 255, message = "Username không điền quá 255 ký tự")
	private String username;
//	@NotBlank(message = "Vui lòng nhập password")
	@Size(max = 255, message = "Password không điền quá 255 ký tự")
	private String password;
	private String photo;
//	@NotEmpty(message = "Vui lòng chọn photo")
	private MultipartFile multiImage;
	private int activated;
	private int admin;
}
