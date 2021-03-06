package com.example.demo.controller.user;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.beans.CartModel;
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
import com.example.demo.untils.EncryptUtil;

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

	// form ????ng nh???p
	@GetMapping("/login-form")
	public String loginForm(Model model, @ModelAttribute("login") LoginModel login) {
		String view = "/views/admin/account/login.jsp";
		model.addAttribute("view", view);
		return "/layout";
	}

	// ????ng nh???p
	@PostMapping("/login")
	public String login(Model model, HttpSession session, @Validated @ModelAttribute("login") LoginModel login,
			BindingResult result) {
		if (result.hasErrors()) {
			String view = "/views/admin/account/login.jsp";
			model.addAttribute("view", view);
			return "/layout";
		} else {
			String username = login.getUsername();
			String password = login.getPassword();

			Account account = accRepository.findByUsername(username);
			if (account != null) {
				boolean check = EncryptUtil.check(password, account.getPassword());
				if (check == true) {
					session.setAttribute("userLogin", account);
					return "redirect:/home";
				} else {
					session.setAttribute("error", "M???t kh???u kh??ng ch??nh x??c!");
					return "redirect:/login-form";
				}
			} else {
				session.setAttribute("error", "T??i kho???n kh??ng t???n t???i!");
			}

		}
		return "redirect:/login-form";
	}

	// l???c s???n ph???m
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

	// ????ng xu???t
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.removeAttribute("userLogin");
		session.removeAttribute("hoaDonMoi");
		System.out.println("????ng th??nh c??ng");
		return "redirect:/home";
	}

	// hi???n th??? r??? h??ng
	@GetMapping("cart")
	public String cart(Model model, HttpSession session) {
		HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("hoaDonMoi");

		model.addAttribute("listCart", cart);

		int thanhTien = 0;
		int tongTien = 0;

		if (cart != null) {
			Set<Integer> keySet = cart.keySet();
			for (Integer x : keySet) {
				thanhTien = cart.get(x).getQuantity() * cart.get(x).getProduct().getPrice();
				System.out.println("Th??nh ti???n for: " + thanhTien);
				tongTien += thanhTien;
				System.out.println("T???ng ti???n for: " + tongTien);
			}
//			session.setAttribute("thanhTien", thanhTien);
			model.addAttribute("tongTien", tongTien);
			model.addAttribute("khoangTrang", " ");
		}
		System.out.println(cart);
		String view = "/views/homes/cart.jsp";
		model.addAttribute("view", view);

		return "/layout";
	}

	// th??m s???n ph???m v??o r??? h??ng
	@GetMapping("addToCart/{id}")
	public String addToCart(Model model, @PathVariable("id") Product prd, HttpSession session,
			@RequestParam("quantity") Integer quantity) {

		int idPrd = prd.getId();
		CartModel cartModel;

		if (quantity < 0) {
			quantity = 1;
		}

		HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("hoaDonMoi");
		if (cart == null) {
			cart = new HashMap<Integer, CartModel>();
			cartModel = new CartModel(prd, quantity);
			cart.put(idPrd, cartModel);
		} else {
			if (cart.containsKey(idPrd)) {
				cartModel = cart.get(idPrd);
				cartModel.setQuantity(cartModel.getQuantity() + quantity);
			} else {
				cartModel = new CartModel(prd, quantity);
				cart.put(idPrd, cartModel);
			}
		}

		session.setAttribute("hoaDonMoi", cart);
		System.out.println("Cart hashMap:" + cart);

		return "redirect:/cart";
	}

	@PostMapping("/updateQuantity/{key}")
	public String updateQuantity(@RequestParam("quantity") Integer quantity, @PathVariable("key") Integer key,
			HttpSession session) {
		HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("hoaDonMoi");
		CartModel productCart;

		if (cart.containsKey(key)) {
			productCart = cart.get(key);
			if (quantity < 1) {
				quantity = 1;
			}
			productCart.setQuantity(quantity);
		}
		System.out.println(cart);
		session.setAttribute("hoaDonMoi", cart);
		return "redirect:/cart";
	}

	// xo?? sp trong r??? h??ng
	@GetMapping("removePrdOnCart/{id}")
	public String removePrdOnCart(@PathVariable("id") Integer id, HttpSession session) {
		System.out.println("id xo??: " + id);

		HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("hoaDonMoi");

		if (cart != null) {
			if (cart.containsKey(id)) {
				cart.remove(id);
				System.out.println("Xo?? th??nh c??ng!");
			}
		}

		session.setAttribute("hoaDonMoi", cart);

		return "redirect:/cart";
	}

	// ?????t h??ng
	@GetMapping("dathang")
	public String dathang(HttpSession session, Model model, @RequestParam("address") String address,
			@RequestParam("sdt") String sdt) {
		Account acc = (Account) session.getAttribute("userLogin");

		LocalDate localDate = LocalDate.now();
		Order order = new Order();
		order.setAccount(acc);
		order.setCreateDate(localDate);
		order.setAddress(address);
		order.setSdt(sdt);
		order.setStatus(1);
		this.orderRepository.save(order);

		HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("hoaDonMoi");

		Set<Integer> keySet = cart.keySet();
		for (Integer x : keySet) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setPrice(cart.get(x).getQuantity() * cart.get(x).getProduct().getPrice());
			orderDetail.setProduct(cart.get(x).getProduct());
			orderDetail.setQuantity(cart.get(x).getQuantity());
			this.orderDetailRepository.save(orderDetail);
		}

		session.setAttribute("message", "B???n ???? ?????t h??ng th??nh c??ng!");
		
		session.removeAttribute("thanhTien");
		session.removeAttribute("hoaDonMoi");

		return "redirect:/users/histories";
	}

}
