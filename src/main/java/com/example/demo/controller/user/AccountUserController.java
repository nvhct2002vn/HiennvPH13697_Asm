package com.example.demo.controller.user;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.beans.AccountModel;
import com.example.demo.entities.Account;
import com.example.demo.repository.AccountRepository;
import com.mysql.cj.Session;

@Controller
@RequestMapping("/register")
public class AccountUserController {
	@Autowired
	AccountRepository accountRepo;

	@Autowired
	ServletContext context;

	@GetMapping("/register-form")
	public String create(Model model, @ModelAttribute("entity") AccountModel account) {
		String view = "/views/users/createAccountUsers.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@PostMapping("store")
	public String store(Model model, @Validated @ModelAttribute("entity") AccountModel accountModel,
			BindingResult result,HttpSession session) {
		if (result.hasErrors()) {
			String view = "/views/users/createAccountUsers.jsp";
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
				accountModel.setPhoto("hiennv.png");
			}
			account.setPhoto(accountModel.getPhoto());
			account.setAdmin(0);
			account.setActivated(0);
			this.accountRepo.save(account);
			session.setAttribute("message", "Đăng ký thành công");
			System.out.println("account: " + account.getPhoto());
		}
		return "redirect:/login-form";

	}
}
