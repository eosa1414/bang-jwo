package com.bangjwo.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(
	name = "JWT",
	type = SecuritySchemeType.HTTP,
	scheme = "bearer",
	bearerFormat = "JWT"
)
public class SwaggerConfig {
	@Bean
	public OpenAPI springBangJwoOpenAPI() {
		return new OpenAPI()
			.info(new Info().title("BangJwo API")
				.description("BangJwo service application")
				.version("v0.0.1")
				.license(new License().name("SSAFY BangJwo")
					.url("https://lab.ssafy.com/s12-blochain-transaction-sub1/S12P21A404")));
	}
}
