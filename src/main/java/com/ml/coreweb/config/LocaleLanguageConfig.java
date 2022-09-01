package com.ml.coreweb.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * BookWormV2
 * Created on 19/8/22 - Friday
 * User Khan, C M Abdullah
 * Ref:
 */
@Slf4j
@Configuration
public class LocaleLanguageConfig extends AcceptHeaderLocaleResolver {
	List<Locale> LOCALES = Arrays.asList(
			new Locale("en"), new Locale("ja"));
	
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
		return StringUtils.isBlank(acceptLanguage) ? Locale.JAPANESE :
					   Locale.lookup(Locale.LanguageRange.parse(acceptLanguage), LOCALES);
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("i18n/messages");
		source.setDefaultEncoding("UTF-8");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	
	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}
	
}
