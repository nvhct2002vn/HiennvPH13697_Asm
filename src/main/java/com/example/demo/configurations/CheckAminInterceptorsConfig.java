package com.example.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Interceptors.CheckAminInterceptors;

@Configuration
public class CheckAminInterceptorsConfig implements WebMvcConfigurer {
	@Autowired
	private CheckAminInterceptors checkAminInterceptors;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(checkAminInterceptors).addPathPatterns("/admin/**").excludePathPatterns("/login-form");
	}

}
