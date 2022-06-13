package com.example.demo.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordModel {

	@NotBlank(message = "Vui lòng điền mật khẩu cũ.")
	@Size(max = 255, message = "Mật khẩu cũ không điền quá 255 ký tự")
	private String mkCu;
	@NotBlank(message = "Vui lòng điền mật khẩu mới.")
	@Size(max = 255, message = "Mật khẩu mới không điền quá 255 ký tự")
	private String mkMoi;
	@NotBlank(message = "Vui lòng điền nhập lại mật khẩu mới.")
	@Size(max = 255, message = "Mật khẩu mới không điền quá 255 ký tự")
	private String mkMoi2;
}
