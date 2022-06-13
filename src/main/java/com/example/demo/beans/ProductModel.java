package com.example.demo.beans;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	private int id;
	@NotBlank(message = "Vui lòng nhập tên sản phẩm.")
	@Size(max = 255, message = "Tên sản phẩm không điền quá 255 ký tự")
	private String name;
	private String image;
	private MultipartFile multiImage;
	@NotNull(message = "Vui lòng nhập giá sản phẩm.")
	@Min(value = 0, message = "Giá sản phẩm không được bé hơn 0.")
//	@Max(value = 10, message = "Giá sản phẩm không được vượt quá 10.")
	private int price;
	private Date createdDate;
	private int available;
	private Category category;
}
