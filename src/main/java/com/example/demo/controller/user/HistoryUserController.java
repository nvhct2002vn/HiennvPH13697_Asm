package com.example.demo.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Account;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderDetail;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.OrderRepository;

@Controller
@RequestMapping("/users")
public class HistoryUserController {
	@Autowired
	private AccountRepository accRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@GetMapping("/histories")
	public String histories(HttpSession session, Model model
//			,@RequestParam(name = "page", defaultValue = ("0")) Integer page,
//			@RequestParam(name = "size", defaultValue = ("10")) Integer size,
//			@RequestParam(name = "field", defaultValue = ("id")) String field
	) {

		Account acc = (Account) session.getAttribute("userLogin");
		int idAcc = acc.getId();
//		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
//		Page<Order> order = this.orderRepository.findAll(pageable);
		List<Order> order = this.orderRepository.getAllByIDUser(idAcc);
		model.addAttribute("order", order);
		String view = "/views/users/history-order.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@GetMapping("/history-details/{id}")
	public String historyDetails(HttpSession session, Model model, @PathVariable("id") Order order) {

		List<OrderDetail> lstCartdt = this.orderDetailsRepository.getAllByIDCart(order.getId());
		model.addAttribute("lstCartdt", lstCartdt);

		model.addAttribute("diaChi", order.getAddress());
		model.addAttribute("sdt", order.getSdt());
		model.addAttribute("status", order.getStatus());
		model.addAttribute("khoangTrang", " ");

		Long tongTienDetails = this.orderDetailsRepository.SumDetails(order.getId());
		model.addAttribute("tongTienDetails", tongTienDetails);

		String view = "/views/users/history-order-details.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@GetMapping("huydonhang/{id}")
	public String huydonhang(HttpSession session, Model model, @PathVariable("id") Order order) {
		model.addAttribute("idOrder", session.getAttribute("idOrder"));
		try {
			session.setAttribute("message", "Huỷ đặt hàng thành công!");
			order.setStatus(0);
			this.orderRepository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/users/histories";
	}

	@GetMapping("datlaidonhang/{id}")
	public String datlaidonhang(HttpSession session, Model model, @PathVariable("id") Order order) {
		model.addAttribute("idOrder", session.getAttribute("idOrder"));
		try {
			session.setAttribute("message", "Huỷ đặt hàng thành công!");
			order.setStatus(1);
			this.orderRepository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/users/histories";
	}
}
