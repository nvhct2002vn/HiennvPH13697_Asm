package com.example.demo.beans;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordModel {

	@NotBlank(message = "Vui lòng điền mật khẩu cũ.")
	private String mkCu;
	@NotBlank(message = "Vui lòng điền mật khẩu mới.")
	private String mkMoi;
	@NotBlank(message = "Vui lòng điền nhập lại mật khẩu mới.")
	private String mkMoi2;
}
