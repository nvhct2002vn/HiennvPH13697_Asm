package com.example.demo.beans;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
	private int id;
	@NotBlank(message = "Vui lòng nhập loại sản phẩm.")
	private String name;
}
