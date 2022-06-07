package com.example.demo.controller.admin;

import java.text.NumberFormat;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.beans.OrderModel;
import com.example.demo.entities.Account;
import com.example.demo.entities.Category;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderDetail;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.OrderRepository;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private AccountRepository accRepository;
	@Autowired
	private OrderDetailsRepository orderDetailRepository;

	@GetMapping("/index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = ("0")) Integer page,
			@RequestParam(name = "size", defaultValue = ("10")) Integer size,
			@RequestParam(name = "field", defaultValue = ("id")) String field) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<Order> order = this.orderRepository.findAll(pageable);
		model.addAttribute("order", order);
		String view = "/views/admin/orders/index.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@GetMapping("create")
	public String create(Model model, @ModelAttribute("orderCreate") OrderModel orderCreate) {
		List<Account> listAcc = this.accRepository.findAll();
		model.addAttribute("listAcc", listAcc);

		String view = "/views/admin/orders/create.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@PostMapping("store")
	public String store(OrderModel orderCreate) {
		LocalDate localDate = LocalDate.now();

		Order order = new Order();

		order.setAccount(orderCreate.getAccount());
		order.setCreateDate(localDate);
		order.setAddress(orderCreate.getAddress());

		this.orderRepository.save(order);

		return "redirect:/admin/orders/index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Order orderEdit) {
		List<Account> listAcc = this.accRepository.findAll();
		model.addAttribute("listAcc", listAcc);

		model.addAttribute("orderEdit", orderEdit);

		String view = "/views/admin/orders/edit.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") Order orderUpdate, OrderModel orderModel) {
		LocalDate localDate = LocalDate.now();

		orderUpdate.setAccount(orderModel.getAccount());
		orderUpdate.setAddress(orderModel.getAddress());
		orderUpdate.setCreateDate(localDate);
		this.orderRepository.save(orderUpdate);

		return "redirect:/admin/orders/index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Order orderDelete) {
		this.orderRepository.delete(orderDelete);
		return "redirect:/admin/orders/index";
	}

	// xem chi tiết hoá đơn
	@GetMapping("order-details/{id}")
	public String orderDetails(@PathVariable("id") Order order, Model model, HttpSession session) {

		int id = order.getId();
		if (order != null) {
			List<OrderDetail> lstCartdt = this.orderDetailRepository.getAllByIDCart(id);
			model.addAttribute("lstCartdt", lstCartdt);
			model.addAttribute("khoangTrang", " ");
		}
		model.addAttribute("Buttonstatus", order.getStatus());
		System.out.println("Buttonstatus: " + order.getStatus());

		Long tongTienDetails = this.orderDetailRepository.SumDetails(order.getId());

		// foramt tiền
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(tongTienDetails);

		System.out.println("Tong tien details: " + moneyString);

		session.setAttribute("tongTien", moneyString);

		String view = "/views/admin/orders/orderDetails.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}
}
