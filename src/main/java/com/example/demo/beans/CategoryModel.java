package com.example.demo.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
	private int id;
	@NotBlank(message = "Vui lòng nhập loại sản phẩm.")
	@Size(max = 255, message = "Loại sản phẩm không điền quá 255 ký tự")
	private String name;
}
