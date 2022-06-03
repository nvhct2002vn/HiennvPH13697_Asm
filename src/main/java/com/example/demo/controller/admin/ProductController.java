package com.example.demo.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.beans.ProductModel;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
	@Autowired
	private ProductRepository prdRepository;
	@Autowired
	private CategoryRepository cateRepository;

	@Autowired
	ServletContext context;

	@GetMapping("/index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = ("0")) Integer page,
			@RequestParam(name = "size", defaultValue = ("5")) Integer size,
			@RequestParam(name = "field", defaultValue = ("price")) String field) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<Product> prd = this.prdRepository.findAll(pageable);
		model.addAttribute("prd", prd);

		String view = "/views/admin/products/index.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@GetMapping("create")
	public String create(Model model, @ModelAttribute("prdCreat") ProductModel prdCreat) {

		List<Category> listCate = this.cateRepository.findAll();
		model.addAttribute("listCate", listCate);

		String view = "/views/admin/products/create.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@PostMapping("store")
	public String store(@Validated @ModelAttribute("prdCreat") ProductModel prdCreate, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			List<Category> listCate = this.cateRepository.findAll();
			model.addAttribute("listCate", listCate);

			String view = "/views/admin/products/create.jsp";
			model.addAttribute("view", view);
			return "/layout";
		} else {
			try {
				LocalDate localDate = LocalDate.now();

				Category cate = this.cateRepository.getById(prdCreate.getCategory().getId());

				Product prd = new Product();

				prd.setCategory(cate);
				prd.setName(prdCreate.getName());

//				Thêm ảnh
				if (!prdCreate.getMultiImage().isEmpty()) {
					String path = context.getRealPath("/photoProducts");
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName = prdCreate.getMultiImage().getOriginalFilename();
						File finalFile = new File(file.getAbsoluteFile() + File.separator + fileName);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalFile));
						stream.write(prdCreate.getMultiImage().getBytes());
						stream.close();

						prdCreate.setImage(fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					prdCreate.setImage(null);
				}
				prd.setImage(prdCreate.getImage());

				prd.setPrice(prdCreate.getPrice());
				prd.setCreatedDate(localDate);
				prd.setAvailable(1);

				this.prdRepository.save(prd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/products/index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Product prdEdit) {
		model.addAttribute("prdEdit", prdEdit);
		System.out.println("ID của category: " + prdEdit.getCategory().getId());

		List<Category> listCate = this.cateRepository.findAll();
		model.addAttribute("listCate", listCate);

		String view = "/views/admin/products/edit.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@PostMapping("update/{id}")
	public String update(Model model, @PathVariable("id") Integer id,
			@Validated @ModelAttribute("prdEdit") ProductModel prdUpdate, BindingResult result) {
		LocalDate localDate = LocalDate.now();

		if (result.hasErrors()) {
			List<Category> listCate = this.cateRepository.findAll();
			model.addAttribute("listCate", listCate);

			String view = "/views/admin/products/edit.jsp";
			model.addAttribute("view", view);

			return "/layout";
		} else {
			Category cate = this.cateRepository.getById(prdUpdate.getCategory().getId());

			Product prd = this.prdRepository.getById(id);

			prd.setCategory(cate);
			prd.setName(prdUpdate.getName());

//			Thêm ảnh
			if (!prdUpdate.getMultiImage().isEmpty()) {
				String path = context.getRealPath("/photoProducts");
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					String fileName = prdUpdate.getMultiImage().getOriginalFilename();
					File finalFile = new File(file.getAbsoluteFile() + File.separator + fileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalFile));
					stream.write(prdUpdate.getMultiImage().getBytes());
					stream.close();

					prdUpdate.setImage(fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				prdUpdate.setImage(null);
			}
			prd.setImage(prdUpdate.getImage());
			prd.setPrice(prdUpdate.getPrice());
			prd.setCreatedDate(localDate);
			prd.setAvailable(prdUpdate.getAvailable());

			this.prdRepository.save(prd);
		}
		return "redirect:/admin/products/index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Product prdDel) {
		this.prdRepository.delete(prdDel);
		return "redirect:/admin/products/index";
	}
}
