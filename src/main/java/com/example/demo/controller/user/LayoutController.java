package com.example.demo.controller.user;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.beans.LoginModel;
import com.example.demo.beans.OrderModel;
import com.example.demo.entities.Account;
import com.example.demo.entities.Category;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.Product;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

@Controller
public class LayoutController {

	@Autowired
	private ProductRepository prdRepository;
	@Autowired
	private CategoryRepository cateRepository;
	@Autowired
	private AccountRepository accRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailsRepository orderDetailRepository;

	@GetMapping("")
	public String home(Model model) {
		List<Product> dsPrd = this.prdRepository.findAll();
		model.addAttribute("dsPrd", dsPrd);
		String view = "/views/homes/home.jsp";
		model.addAttribute("view", view);
		model.addAttribute("khoangTrang", " ");
		return "/layout";
	}

	@GetMapping("/home")
	public String homelo(Model model) {
		List<Product> dsPrd = this.prdRepository.findAll();
		model.addAttribute("dsPrd", dsPrd);
		String view = "/views/homes/home.jsp";
		model.addAttribute("view", view);
		model.addAttribute("khoangTrang", " ");
		return "/layout";
	}

	@GetMapping("/collections/allitems")
	public String listProduct(Model model) {
		List<Product> dsPrd = this.prdRepository.findAll();
		model.addAttribute("dsPrd", dsPrd);

		List<Category> dsCate = this.cateRepository.findAll();
		model.addAttribute("dsCate", dsCate);

		model.addAttribute("khoangTrang", " ");

		String view = "/views/homes/list-products.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@GetMapping("/products/{id}")
	public String products(Model model, @PathVariable("id") Product prd) {

		model.addAttribute("prd", prd);

		model.addAttribute("khoangTrang", " ");

		String view = "/views/homes/products.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@GetMapping("/login-form")
	public String loginForm(Model model, @ModelAttribute("login") LoginModel login) {
		String view = "/views/admin/account/login.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@PostMapping("/login")
	public String login(Model model, HttpSession session, @Validated @ModelAttribute("login") LoginModel login,
			BindingResult result) {
		if (result.hasErrors()) {
			String view = "/views/admin/account/login.jsp";
			model.addAttribute("view", view);
			return "/layout";
		} else {
			List<Account> listAccounts = this.accRepository.findAll();

			for (Account x : listAccounts) {
				if (x.getUsername().equals(login.getUsername()) && x.getPassword().equals(login.getPassword())) {
					session.setAttribute("userLogin", x);
					System.out.println("login thành công: " + x.getFullname());
					return "redirect:/home";
				}
			}
		}
		return "redirect:/login-form";
	}

	@GetMapping("/fill-products/{id}")
	public String addPrdToCard(Model model, @PathVariable("id") Category entity) {
		List<Product> dsPrd = this.prdRepository.getAllByIDCate(entity.getId());
		List<Category> dsCate = this.cateRepository.findAll();

		model.addAttribute("khoangTrang", " ");
		model.addAttribute("dsCate", dsCate);
		model.addAttribute("dsPrd", dsPrd);
		String view = "/views/homes/list-products-filter.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.removeAttribute("userLogin");
		session.removeAttribute("hoaDonMoi");
		System.out.println("Đăng thành công");
		return "redirect:/home";
	}

	@GetMapping("cart")
	public String cart(@ModelAttribute("entity") OrderModel entity, Model model, HttpSession session) {

		Order order = (Order) session.getAttribute("hoaDonMoi");
		if (order != null) {
			int id = order.getId();
			session.setAttribute("idOrder", id);
			List<OrderDetail> lstCartdt = this.orderDetailRepository.getAllByIDCart(id);
			model.addAttribute("lstCartdt", lstCartdt);
			model.addAttribute("khoangTrang", " ");
			session.setAttribute("lstCartdt", lstCartdt);
//			model.addAttribute("idCart", order.getId()); // lấy id để gán vào nút button
			Long tongTienDetails = this.orderDetailRepository.SumDetails(order.getId());
			System.out.println("Tong tiền: " + tongTienDetails);
			session.setAttribute("tongTien", tongTienDetails);
		}

//
//		// foramt tiền
//		NumberFormat formatter = NumberFormat.getCurrencyInstance();
//		String moneyString = formatter.format(tongTienDetails);
//
//		System.out.println("Tong tien details: " + moneyString);
//

		String view = "/views/homes/cart.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	@ModelAttribute("categoryIds")
	public Map<Integer, String> getCategory() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<Category> list = cateRepository.findAll();
		for (Category x : list) {
			map.put(x.getId(), x.getName());
		}
		return map;
	}

	@GetMapping("addToCart/{id}")
	public String addToCart(Model model, @PathVariable("id") Product prd, HttpSession session) {
		try {
			Order order = (Order) session.getAttribute("hoaDonMoi");
			Account account = (Account) session.getAttribute("userLogin");

			if (account == null) {
				account = this.accRepository.getById(26);
			}

			if (order == null) {
				order = new Order();
				LocalDate localDate = LocalDate.now();
				order.setAccount(account);
				order.setCreateDate(localDate);
				order.setAddress("Hoá đơn chờ.");
				this.orderRepository.save(order);
				session.setAttribute("hoaDonMoi", order);
				System.out.println("Tạo hoá đơn thành công!");
			} else {
				System.out.println("Hoá đơn đã tồn tại: " + session.getAttribute("hoaDonMoi"));
			}
			Order order2 = (Order) session.getAttribute("hoaDonMoi");
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order2);
			orderDetail.setProduct(prd);

			int soLuong = 0;
			int idOrderDT = 0;
			// id sản phẩm
			int idPrd = prd.getId();
			// lấy ra list orderDetails có mã order là ... ví dụ có 3
			List<OrderDetail> listOrderDetails = this.orderDetailRepository.getAllByIDCart(order.getId());
			// duyệt list order: listdt sẽ chạy số lần = số lượng ordt dùng chung ord vd
			// chạy 3 lần
			for (OrderDetail x : listOrderDetails) {
				session.setAttribute("tongTien", x.getPrice());
				// kiểm tra, nếu sản phẩm trong orderDetails giống sp lúc ấn chọn, thì số lượng
				// sẽ đc cộng lên, và gán id orderdetails đc cộng đó ra để tý update
				if (x.getProduct().getId() == idPrd) {
					soLuong += x.getQuantity();
					idOrderDT = x.getId();
				}
			}
			// kiểm tra nếu số lượng lớn hơn 0( đã tồn tại) thì sửa đối tượng được lấy ra
			// bên trên, còn không thì sẽ thêm mới
			if (soLuong > 0) {
				// tìm ra đối tượng đã tồn tại
				OrderDetail ordUpdate = this.orderDetailRepository.getById(idOrderDT);
				System.out.println("Số lượng: " + soLuong);
				ordUpdate.setPrice(prd.getPrice() * (soLuong + 1));
				ordUpdate.setQuantity(soLuong + 1);
				this.orderDetailRepository.save(ordUpdate);
			} else {
				orderDetail.setQuantity(1);
				orderDetail.setPrice(prd.getPrice());
				this.orderDetailRepository.save(orderDetail);
				System.out.println("Tạo thành công hoá đơn chi tiết!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cart";
	}

	@GetMapping("removePrdOnCart/{id}")
	public String removePrdOnCart(@PathVariable("id") OrderDetail ord) {
		this.orderDetailRepository.delete(ord);
		return "redirect:/cart";
	}

	@PostMapping("dathang/{id}")
	public String dathang(HttpSession session, Model model, @PathVariable("id") Order order,
			@Validated @ModelAttribute("entity") OrderModel entity, BindingResult result) {
		model.addAttribute("idOrder", session.getAttribute("idOrder"));
		if (result.hasErrors()) {
			session.getAttribute("lstCartdt");

			String view = "/views/homes/cart.jsp";
			model.addAttribute("view", view);

			return "/layout";
		} else {
			try {
				order.setAddress(entity.getAddress());
				this.orderRepository.save(order);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/cart";
	}
}
