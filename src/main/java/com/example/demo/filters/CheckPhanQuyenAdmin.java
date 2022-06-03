package com.example.demo.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.entities.Account;

/**
 * Servlet Filter implementation class CheckPhanQuyenAdmin
 */
@WebFilter(urlPatterns = { "/admin/*" })
public class CheckPhanQuyenAdmin extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public CheckPhanQuyenAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();

		Account user = (Account) session.getAttribute("userLogin"); // lấy ra user từ session lúc đăng nhập

		// user == null &&
		if (user.getAdmin() == 0) {
			httpRes.sendRedirect("/HiennvPH13697_Asm/home");
			return;
		}

		chain.doFilter(request, response); // kiểm tra
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
