package com.ml.coreweb.exception;

import com.ml.coreweb.util.DateTimeUtil;
import org.slf4j.MDC;

import java.util.Map;

/**
 * BookWormV2
 * Created on 19/8/22 - Friday
 * User Khan, C M Abdullah
 * Ref:
 */
public class ApiErrorResponse {
	
	public final String requestId = MDC.get("requestId");
	private final Long responseTime = DateTimeUtil.timeNowToEpochSecond();
	private final Boolean hasError = true;
	private final String message;
	private final Map<String, String> errors;
	
	public ApiErrorResponse(String message, Map<String, String> errors) {
		this.message = message;
		this.errors = errors;
	}
	
	public Long getResponseTime() {
		return responseTime;
	}
	
	public Boolean getHasError() {
		return hasError;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public String getRequestId() {
		return requestId;
	}
}

