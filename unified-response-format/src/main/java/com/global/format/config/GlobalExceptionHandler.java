package com.global.format.config;

import com.global.format.response.DefaultHttpResult;
import com.global.format.response.constant.PlatformCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	public @ResponseBody
    Object exceptionHandler(Exception exception) {
		DefaultHttpResult httpResult = new DefaultHttpResult();
		if (exception instanceof MethodArgumentNotValidException) {
			BindingResult bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
			StringBuilder strBuilder = new StringBuilder();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				strBuilder.append(fieldError.getDefaultMessage()).append("/");
			}
			String msg = strBuilder.toString();
			logger.error("捕获到参数校验异常:{}, exception:", msg, exception);
			httpResult.setCode(PlatformCode.PARAM_ERROR.getCode());
			httpResult.setMessage(msg);
		} else {
			logger.error("捕获到系统错误:{}, exception:", exception.getMessage(), exception);
			httpResult.setCode(PlatformCode.SYSTEM_ERROR.getCode());
			httpResult.setMessage(exception.getMessage());
		}
		return httpResult;
	}
}
