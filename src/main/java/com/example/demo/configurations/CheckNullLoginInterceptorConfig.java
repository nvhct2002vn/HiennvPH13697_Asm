package com.example.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Interceptors.CheckNotNullLoginInterceptor;

@Configuration
public class CheckNullLoginInterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private CheckNotNullLoginInterceptor CheckNotNullLoginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(CheckNotNullLoginInterceptor).addPathPatterns("/register-form", "/login-form");
	}

}
