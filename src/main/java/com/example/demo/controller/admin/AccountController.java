package com.example.demo.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Location;
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

import com.example.demo.beans.AccountModel;
import com.example.demo.entities.Account;
import com.example.demo.repository.AccountRepository;

@Controller
@RequestMapping("/admin/accounts")
public class AccountController {
	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	ServletContext context;

	@GetMapping("index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = ("0")) Integer page,
			@RequestParam(name = "size", defaultValue = ("10")) Integer size,
			@RequestParam(name = "field", defaultValue = ("id")) String field) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<Account> data = this.accountRepo.findAll(pageable);
		model.addAttribute("data", data);
		String view = "/views/admin/accounts/index.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@GetMapping("create")
	public String create(Model model, @ModelAttribute("entity") AccountModel account) {
		String view = "/views/admin/accounts/create.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@PostMapping("store")
	public String store(Model model, @Validated @ModelAttribute("entity") AccountModel accountModel,
			BindingResult result) {
		if (result.hasErrors()) {
			String view = "/views/admin/accounts/create.jsp";
			model.addAttribute("view", view);

			return "/layout";
		} else {
			Account account = new Account();
			account.setFullname(accountModel.getFullname());
			account.setEmail(accountModel.getEmail());
			account.setUsername(accountModel.getUsername());
			String encrypted = com.example.demo.untils.EncryptUtil.encrypt(accountModel.getPassword()); // lấy password
																										// và
																										// mã hoá
			account.setPassword(encrypted);

//			Thêm ảnh
			if (!accountModel.getMultiImage().isEmpty()) {
				String path = context.getRealPath("/photoAccounts");
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					String fileName = accountModel.getMultiImage().getOriginalFilename();
					File finalFile = new File(file.getAbsoluteFile() + File.separator + fileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalFile));
					stream.write(accountModel.getMultiImage().getBytes());
					stream.close();

					accountModel.setPhoto(fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				accountModel.setPhoto(null);
			}
			account.setPhoto(accountModel.getPhoto());
			account.setAdmin(accountModel.getAdmin());
			account.setActivated(0);
			this.accountRepo.save(account);
			System.out.println("account: " + account.getPhoto());
		}
		return "redirect:/admin/accounts/index";

	}

	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") Account account) {
		this.accountRepo.delete(account);

		return "redirect:/admin/accounts/index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Account accountEdit) {
		model.addAttribute("entity", accountEdit);
		System.out.println("account của bạn: " + accountEdit.getEmail());

		String view = "/views/admin/accounts/edit.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") Account account, Model model,
			@Valid @ModelAttribute("entity") AccountModel entity, BindingResult result) {
		try {
			System.out.println("Get ảnh: " + model.getAttribute("fullname"));
			if (result.hasErrors()) {
				System.out.println(result.getAllErrors());
				String view = "/views/admin/accounts/edit.jsp";
				model.addAttribute("view", view);
				return "/layout";
			} else {
//				Account account = this.accountRepo.getById(id);

				account.setFullname(entity.getFullname());
				account.setEmail(entity.getEmail());
				account.setUsername(entity.getUsername());

//				Thêm ảnh
				if (!entity.getMultiImage().isEmpty()) {
					String path = context.getRealPath("/photoAccounts");
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName = entity.getMultiImage().getOriginalFilename();
						File finalFile = new File(file.getAbsoluteFile() + File.separator + fileName);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalFile));
						stream.write(entity.getMultiImage().getBytes());
						stream.close();

						entity.setPhoto(fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					entity.setPhoto(null);
				}
				account.setPhoto(entity.getPhoto());
				account.setAdmin(entity.getAdmin());
				account.setActivated(entity.getActivated());

				this.accountRepo.save(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return "redirect:/admin/accounts/index";
	}
}
