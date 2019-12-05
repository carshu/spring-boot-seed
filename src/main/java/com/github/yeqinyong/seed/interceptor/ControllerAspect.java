package com.github.yeqinyong.seed.interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.alibaba.fastjson.JSON;

import com.github.yeqinyong.seed.basemodel.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author 叶勤勇(卡虚)
 * @date 2019/12/04
 **/
@Aspect
@Slf4j
@Component
public class ControllerAspect {
	@Around("within(@org.springframework.web.bind.annotation.RestController *) || "
		+ "within(@org.springframework.stereotype.Controller *)")
	public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {

		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		long start = System.currentTimeMillis();
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (ConstraintViolationException e) {
			// 参数校验异常
			StringBuilder errorMsg = new StringBuilder();
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			for (ConstraintViolation violation : constraintViolations) {
				errorMsg.append("; ")
					.append(violation.getPropertyPath())
					.append(violation.getMessage());
			}
			result = ApiResult.fail("400", errorMsg.substring(2));
		} catch (IllegalArgumentException | IllegalStateException e){
			result = ApiResult.fail("400", e.getMessage());
		} catch (Throwable e) {
			result = ApiResult.fail("500", e.getMessage());
			log.error(e.getMessage(), e);
		}

		try {
			log.info("duration: {}ms, method: {}, params: {}, result: {}",
				System.currentTimeMillis() - start,
				method.getDeclaringClass().getName() + "." + method.getName(),
				joinPoint.getArgs(),
				JSON.toJSONString(result));
		}catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
}
