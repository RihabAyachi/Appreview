package com.rihab.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2

public class AppReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppReviewApplication.class, args);
	}
	
	
	@Bean
	public Docket swaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.rihab.demo"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		
		return new ApiInfo(
				"Review Your Application API",
				"the easiest way to review applications",
				"1.0",
				"Free",
				new springfox.documentation.service.Contact("Rihab Ayachi","", "rihab.ayachi@etudiant-enit.utm.tn"),
				"",
				"",
				Collections.emptyList());
	}

}
