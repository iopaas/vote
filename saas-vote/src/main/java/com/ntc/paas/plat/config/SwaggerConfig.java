package com.ntc.paas.plat.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// 读取配置文件中的enable，true为显示，false为隐藏
	@Value("${swagger2.enable:true}")
	private boolean enable;

	@Bean
	public Docket createDocke() {

		return new Docket(DocumentationType.SWAGGER_2)
				// 进入swagger-ui的信息
				.apiInfo(apiInfo()).select()
				// 暴露所有controller类的所在的包路径
				.apis(RequestHandlerSelectors.basePackage("com.ntc.paas.plat.controller")).paths(PathSelectors.any())
				.build().enable(enable).securitySchemes(securitySchemes()).securityContexts(securityContexts());
	}

	// 进入swagger-ui的信息
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 该项目的名字
				.title("Spring Boot 2.x")
				// 该项目的描述
				.description("spring boot2.x").version("1.0").build();
	}

	private List<ApiKey> securitySchemes() {
		List<ApiKey> apiKeyList = new ArrayList<>();
		apiKeyList.add(new ApiKey("token", "token", "header"));
		return apiKeyList;
	}

	private List<SecurityContext> securityContexts() {
		List<SecurityContext> securityContexts = new ArrayList<>();
		securityContexts.add(SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("^(?!app).*$")).build());
		return securityContexts;
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> securityReferences = new ArrayList<>();
		securityReferences.add(new SecurityReference("token", authorizationScopes));
		return securityReferences;
	}

}
