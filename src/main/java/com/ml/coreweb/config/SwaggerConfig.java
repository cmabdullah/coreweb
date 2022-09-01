package com.ml.coreweb.config;

import com.fasterxml.classmate.TypeResolver;
import com.ml.coreweb.exception.ApiErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.ml.coreweb.constants.CoreWebConstants.*;

/**
 * BookWormV2
 * Created on 19/8/22 - Friday
 * User Khan, C M Abdullah
 * Ref:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${applicationName}")
	private String applicationName;

	@Value("${app.base-url}")
	private String appUrl;

	@Value("${contactEmail}")
	private String contactEmail;

	@Bean
	public Docket api() {

		List<Response> responseMessage = Arrays.asList(
				new ResponseBuilder().code("200")
						.description(SUCCESS).build(),
				new ResponseBuilder().code("401")
						.description(NOT_AUTHORIZED).build(),
				new ResponseBuilder().code("403")
						.description(FORBIDDEN).build(),
				new ResponseBuilder().code("404")
						.description(RESOURCES_NOT_FOUND).build(),
				new ResponseBuilder().code("500")
						.description(INTERNAL_SERVER_ERROR).build());

		TypeResolver typeResolver = new TypeResolver();
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.additionalModels(typeResolver.resolve(ApiErrorResponse.class))
				.securitySchemes(Collections.singletonList(apiKey()))
				.securityContexts(Collections.singletonList(securityContext()))
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.globalResponses(HttpMethod.GET, responseMessage)
				.globalResponses(HttpMethod.POST, responseMessage)
				.globalResponses(HttpMethod.PUT, responseMessage)
				.globalResponses(HttpMethod.DELETE, responseMessage);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(this.applicationName + " Api").description("")
				.termsOfServiceUrl(this.appUrl + "/terms")
				.contact(new Contact(this.applicationName + " MC", this.appUrl, this.contactEmail))
				.license("LGPL").licenseUrl(this.appUrl + "/license").version("1.0.0").build();
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private ApiKey apiKey() {
		return new ApiKey(BEARER, AUTHORIZATION, HEADER);
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope[] authorizationScopes = {
				new AuthorizationScope(AUTHORIZATION_SCOPE_GLOBAL, AUTHORIZATION_SCOPE_GLOBAL_DESC)};
		return Collections.singletonList(new SecurityReference(BEARER, authorizationScopes));
	}
}