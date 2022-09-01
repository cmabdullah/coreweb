package com.ml.coreweb.interceptors;

import com.ml.coreweb.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BookWormV2
 * Created on 19/8/22 - Friday
 * User Khan, C M Abdullah
 * Ref:
 */
@Component
@Slf4j
public class ActivityInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String requestId = request.getHeader("requestId");
		if (StringUtils.isBlank(requestId)) {
			MDC.put("requestId", Utils.getUniqueUUID());
		}
		log.debug("preHandle" + request);
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response, Object handler, ModelAndView modelAndView) {
	
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception exception) {
		MDC.remove("requestId");
	}
	
	
	//todo
	private String getParameters(HttpServletRequest request) {
		return null;
	}
	//todo
	private String getRemoteAddr(HttpServletRequest request) {
		return null;
	}
}
