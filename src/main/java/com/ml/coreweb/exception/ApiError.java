package com.ml.coreweb.exception;

import com.ml.coreweb.util.TranslateMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * BookWormV2
 * Created on 19/8/22 - Friday
 * User Khan, C M Abdullah
 * Ref:
 */
public class ApiError extends RuntimeException {
	
	private final String errorMessage;
	private final HttpStatus status;
	private Object[] args;
	private Map<String, String> errors = new HashMap<>();
	
	/**
	 * @param errorMessage {@link String} -> error message
	 * @param status       {@link HttpStatus} -> status code what we have to return through api
	 */
	public static Supplier<? extends ApiError> createSingletonSupplier(String errorMessage, HttpStatus status) {
		return () -> new ApiError(errorMessage, status);
	}
	
	public static void create(HttpServletResponse response, Exception exception) {
		ResponseEntity<?> error = RestExceptionHandler.handleAnyException(exception);
		response.setStatus(error.getStatusCodeValue());
		response.setStatus(10);
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
//		response.sendError(error.getBody().toString());
		//response.sendError(HttpServletResponse.SC_CONFLICT, exception.getMessage().toString());
	}
	
	public ApiError(String errorMessage, HttpStatus status) {
		this.errorMessage = TranslateMessage.getMessage(errorMessage);
		this.status = status;
	}
	
	public void addError(String key, String message) {
		errors.put(key, TranslateMessage.getMessage(message));
	}
	
	public void addAllErrors(Map<String, String> errorMap) {
		errorMap.forEach((key, value) -> errors.put(key, TranslateMessage.getMessage(value)));
		//errors.putAll(errorMap);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public Object[] getArgs() {
		return args;
	}
	
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		return "ApiError{" +
					   "errorMessage='" + errorMessage + '\'' +
					   ", status=" + status +
					   ", args=" + Arrays.toString(args) +
					   ", errors=" + errors +
					   '}';
	}
}
