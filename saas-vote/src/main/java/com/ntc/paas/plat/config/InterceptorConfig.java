package com.ntc.paas.plat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
				// 放掉errorBasicServlet内部方法拦截
				.excludePathPatterns("/**/error", "/api/plat/comm/")
				// 放掉swagger的拦截
				.excludePathPatterns("/swagger-resources/**", "/v2/**", "/swagger-ui.html/**").addPathPatterns("/**");
	}

}