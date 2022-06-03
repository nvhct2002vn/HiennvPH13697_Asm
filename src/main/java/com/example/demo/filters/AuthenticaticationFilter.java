package com.example.demo.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.entities.Account;

@WebFilter(urlPatterns = { "/admin/*" })
public class AuthenticaticationFilter implements Filter {

	public AuthenticaticationFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();

		Account user = (Account) session.getAttribute("userLogin"); // lấy ra user từ session lúc đăng nhập

		if (user == null) {
			httpRes.sendRedirect("/HiennvPH13697_Asm/login-form");
			return;
		}

		chain.doFilter(request, response); // kiểm tra
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
