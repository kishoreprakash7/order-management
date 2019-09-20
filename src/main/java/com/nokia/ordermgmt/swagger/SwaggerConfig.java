package com.nokia.ordermgmt.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Kishore Prakash
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.nokia.ordermgmt"))
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				"Nokia SpringBoot REST API",
				"Nokia SpringBoot REST API for Order Management APP",
				"1.0",
				"Terms of service",
				new Contact("Kishore Prakash", "swagger.io", "kishore.prakash7@gmail.com"),
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}
