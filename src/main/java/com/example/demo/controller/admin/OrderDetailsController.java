package com.example.demo.controller.admin;

import java.util.List;

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

import com.example.demo.beans.OrderDetailsModel;
import com.example.demo.entities.Category;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/admin/order-details")
public class OrderDetailsController {
	@Autowired
	private OrderDetailsRepository orderdetailsRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository prdRepository;

	@GetMapping("/index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = ("0")) Integer page,
			@RequestParam(name = "size", defaultValue = ("5")) Integer size,
			@RequestParam(name = "field", defaultValue = ("order.id")) String field) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<OrderDetail> orderdetails = this.orderdetailsRepository.findAll(pageable);
		model.addAttribute("orderdetails", orderdetails);

		String view = "/views/admin/orderDetails/index.jsp";
		model.addAttribute("view", view);
		model.addAttribute("khoangTrang", " ");

		return "/layout";
	}

	@GetMapping("create")
	public String create(Model model, @ModelAttribute("ordCreate") OrderDetailsModel ordCreate) {

		List<Product> listPrd = this.prdRepository.findAll();
		model.addAttribute("listPrd", listPrd);

		List<Order> listOrder = this.orderRepository.findAll();
		model.addAttribute("listOrder", listOrder);

		model.addAttribute("khoangTrang", " ");

		String view = "/views/admin/orderDetails/create.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@PostMapping("store")
	public String store(Model model, OrderDetailsModel ordCreate) {
		OrderDetail ordDetail = new OrderDetail();

		ordDetail.setOrder(ordCreate.getOrder());
		ordDetail.setProduct(ordCreate.getProduct());
		ordDetail.setPrice(ordCreate.getProduct().getPrice());
		ordDetail.setQuantity(1);

		this.orderdetailsRepository.save(ordDetail);

		return "redirect:/admin/order-details/index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") OrderDetail orderDetailEdit) {
		List<Product> listPrd = this.prdRepository.findAll();
		model.addAttribute("listPrd", listPrd);

		List<Order> listOrder = this.orderRepository.findAll();
		model.addAttribute("listOrder", listOrder);

		model.addAttribute("orderDetailEdit", orderDetailEdit);
		model.addAttribute("khoangTrang", " ");
		String view = "/views/admin/orderDetails/edit.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") OrderDetail orderDetail, OrderDetailsModel orderDetailsUpdate) {

		orderDetail.setOrder(orderDetailsUpdate.getOrder());
		orderDetail.setProduct(orderDetailsUpdate.getProduct());
		orderDetail.setPrice(orderDetailsUpdate.getProduct().getPrice());
		orderDetail.setQuantity(1);

		this.orderdetailsRepository.save(orderDetail);

		return "redirect:/admin/order-details/index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") OrderDetail orderDetailDelete) {
		this.orderdetailsRepository.delete(orderDetailDelete);
		return "redirect:/admin/orders/index";
	}
}
