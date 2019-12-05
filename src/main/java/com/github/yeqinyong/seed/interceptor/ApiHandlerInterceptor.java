package com.github.yeqinyong.seed.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author 叶勤勇(卡虚)
 * @date 2019/07/04
 **/
@Slf4j
public class ApiHandlerInterceptor extends HandlerInterceptorAdapter {

    Map<String, String> apiKeys;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    private void logHttpRequest(HttpServletRequest request) {
        if(request == null) {
            return;
        }

		logHttpRequest(request);

		StringBuilder sb = new StringBuilder();
        sb.append(request.getMethod()).append(" ")
			.append(request.getRequestURI())
            .append("/")
			.append(request.getQueryString())
			.append("\n");

        log.info("{}", sb.toString());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
