package com.ml.coreweb.response;

import com.ml.coreweb.util.DateTimeUtil;
import org.slf4j.MDC;

/**
 * BookWormV2
 * Created on 19/8/22 - Friday
 * User Khan, C M Abdullah
 * Ref:
 */
public class ApiResponse <T> {
	public final String requestId = MDC.get("requestId");
	private final Boolean hasError = false;
	private final Long responseTime = DateTimeUtil.timeNowToEpochSecond();
	private T result;
	
	public ApiResponse(T body) {
		result = body;
	}
	
	public Boolean getHasError() {
		return hasError;
	}
	
	public Long getResponseTime() {
		return responseTime;
	}
	
	public T getResult() {
		return result;
	}
	
	public String getRequestId() {
		return requestId;
	}
}
