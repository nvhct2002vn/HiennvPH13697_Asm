package com.example.demo.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.example.demo.beans.CategoryModel;
import com.example.demo.entities.Account;
import com.example.demo.entities.Category;
import com.example.demo.repository.CategoryRepository;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
	@Autowired
	private CategoryRepository cateRepository;

	@GetMapping("/index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = ("0")) Integer page,
			@RequestParam(name = "size", defaultValue = ("10")) Integer size,
			@RequestParam(name = "field", defaultValue = ("id")) String field) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<Category> cate = this.cateRepository.findAll(pageable);
		model.addAttribute("cate", cate);

		String view = "/views/admin/categories/index.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@GetMapping("create")
	public String create(Model model, @ModelAttribute("categoryCreate") CategoryModel categoryModel) {
		String view = "/views/admin/categories/create.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@PostMapping("store")
	public String store(@Validated @ModelAttribute("categoryCreate") CategoryModel categoryModel, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			String view = "/views/admin/categories/create.jsp";
			model.addAttribute("view", view);

			return "/layout";
		} else {
			Category cate = new Category();
			cate.setName(categoryModel.getName());
			this.cateRepository.save(cate);
		}
		return "redirect:/admin/categories/index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Category categoryEdit) {
		model.addAttribute("categoryEdit", categoryEdit);

		String view = "/views/admin/categories/edit.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("categoryEdit") CategoryModel categoryUpdate, BindingResult result, Model model) {
		if (result.hasErrors()) {
			String view = "/views/admin/categories/edit.jsp";
			model.addAttribute("view", view);

			return "/layout";
		} else {
			Category cate = this.cateRepository.getById(id);

			cate.setName(categoryUpdate.getName());

			this.cateRepository.save(cate);

		}
		return "redirect:/admin/categories/index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Category categoryDel) {
		this.cateRepository.delete(categoryDel);
		return "redirect:/admin/categories/index";
	}
}
