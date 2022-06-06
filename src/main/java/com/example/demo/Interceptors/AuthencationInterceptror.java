package com.example.demo.Interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Account;

@Component
public class AuthencationInterceptror implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("userLogin") == null) {
			session.setAttribute("error", "Vui lòng đăng nhập!");
			response.sendRedirect(request.getContextPath() + "/login-form");
			return false;
		}
		System.out.println("preHandle: " + request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("userLogin");
		if (acc.getAdmin() == 0) {
			response.sendRedirect(request.getContextPath() + "");
		}
		System.out.println("postHandle: " + request.getRequestURI());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion: " + request.getRequestURI());
	}

}
