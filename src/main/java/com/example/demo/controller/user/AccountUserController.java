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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.beans.AccountModel;
import com.example.demo.beans.ChangePasswordModel;
import com.example.demo.entities.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.untils.EncryptUtil;
import com.mysql.cj.Session;

@Controller
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

	@PostMapping("storeRegister")
	public String store(Model model, @Validated @ModelAttribute("entity") AccountModel accountModel,
			BindingResult result, HttpSession session) {
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
			account.setActivated(1);
			this.accountRepo.save(account);
			session.setAttribute("message", "Đăng ký thành công");
			System.out.println("account: " + account.getPhoto());
		}
		return "redirect:/login-form";

	}

	@GetMapping("/change-password")
	public String doiMatKhau(Model model, @ModelAttribute("entity") ChangePasswordModel changePass) {

		String view = "/views/users/change-password.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@PostMapping("/change-password-store")
	public String doiMatKhauStore(@Validated @ModelAttribute("entity") ChangePasswordModel changePass,
			BindingResult result, HttpSession session, Model model) {
		Account acc = (Account) session.getAttribute("userLogin");

		boolean check = EncryptUtil.check(changePass.getMkCu(), acc.getPassword());
		if (result.hasErrors()) {
			String view = "/views/users/change-password.jsp";
			model.addAttribute("view", view);

			return "/layout";
		} else {
			if (acc != null) {
				if (!check) {
					session.setAttribute("error", "Mật cũ không chính xác!");
					return "redirect:/change-password";
				} else if (changePass.getMkMoi().equals(changePass.getMkMoi2())) {
					// lấy mkMoi2 và mã hoá
					String encrypted = com.example.demo.untils.EncryptUtil.encrypt(changePass.getMkMoi2());

					acc.setPassword(encrypted);
					this.accountRepo.save(acc);
				} else if (changePass.getMkMoi() != changePass.getMkMoi2()) {
					session.setAttribute("error", "Mật khẩu mới không khớp!");
					return "redirect:/change-password";
				}
			}
		}
		session.setAttribute("message", "Đổi mật khẩu thành công!");
		return "redirect:/change-password";
	}

	@GetMapping("update-information")
	public String updateInformation(Model model, @ModelAttribute("entity") AccountModel accountModel,
			BindingResult result, HttpSession session) {
		String view = "/views/users/update-information.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@PostMapping("update-information-store")
	public String updateInformationStore(Model model, @Validated @ModelAttribute("entity") AccountModel accountModel,
			BindingResult result, HttpSession session) {

		Account acc = (Account) session.getAttribute("userLogin");

		if (result.hasErrors()) {
			String view = "/views/users/update-information.jsp";
			model.addAttribute("view", view);
			return "/layout";
		} else {
			acc.setFullname(accountModel.getFullname());
			acc.setEmail(accountModel.getEmail());
			acc.setUsername(accountModel.getUsername());

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
				accountModel.setPhoto("user.png");
			}
			acc.setPhoto(accountModel.getPhoto());
			this.accountRepo.save(acc);
		}
		session.setAttribute("message", "Cập nhật thành công!");
		return "redirect:/update-information";
	}
}
