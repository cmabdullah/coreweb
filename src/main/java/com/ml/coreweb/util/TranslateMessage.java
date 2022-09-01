package com.ml.coreweb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * BookWormV2
 * Created on 19/8/22 - Friday
 * User Khan, C M Abdullah
 * Ref:
 */
@Component
public class TranslateMessage {
	private static ResourceBundleMessageSource messageSource;

	@Autowired
	public TranslateMessage(ResourceBundleMessageSource messageSource) {
		TranslateMessage.messageSource = messageSource;
	}

	public static String getMessage(String message) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(message, null, locale);
	}
}