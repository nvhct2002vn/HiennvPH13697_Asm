package com.example.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Interceptors.AuthencationInterceptror;

@Configuration
public class AuthencationInterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private AuthencationInterceptror loginInterceptror;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptror).addPathPatterns("/admin/**", "/cart", "/users/**")
				.excludePathPatterns("/login-form","/register/**");
	}

}
