package com.global.format.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.format.response.DefaultHttpResult;
import com.global.format.response.DefaultPageResp;
import com.global.format.response.IHttpResult;
import com.global.format.response.IPageResp;
import com.global.format.response.constant.PlatformCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @Author yy
 **/
@SuppressWarnings("rawtypes")
@ControllerAdvice(basePackages = "com.global.format.controller")
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice {

	private static final Logger logger = LoggerFactory.getLogger(GlobalResponseBodyAdvice.class);

	//可以使用白名单排查有些不组装结果的接口，如监控，监控检查等接口
	private static final List<String> WHITE_LIST_PATH = Arrays.asList( "/test/common" );


	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		String requestPath = request.getURI().getPath();
		if (WHITE_LIST_PATH.contains(requestPath)) {
			logger.info("GDX-DEBUG request path:{} in response body advice white list.", requestPath);
			return body;
		}
		// 返回值空值处理
		if (body == null) {
			DefaultHttpResult<Object> httpResult = new DefaultHttpResult();
			httpResult.setCode(PlatformCode.SUCCESS.getCode());
			httpResult.setMessage(PlatformCode.SUCCESS.getMessage());
			httpResult.setData(null);
			return httpResult;
		}
		// 兼容Rpc返回值规范
		if (body instanceof IPageResp) {
			IPageResp pageResp = (IPageResp) body;
			DefaultHttpResult<IPageResp> httpResult = new DefaultHttpResult();
			httpResult.setCode(PlatformCode.SUCCESS.getCode());
			httpResult.setMessage(PlatformCode.SUCCESS.getMessage());
			httpResult.setData(DefaultPageResp.buildPageResp(pageResp.getPageNum(), pageResp.getPageSize(),
					pageResp.getTotalCount(), pageResp.getPageRecords()));
			return httpResult;
		} // 兼容之前APi规范老代码
		else if (body instanceof IHttpResult) {
			return body;
		} else {
			// 将新返回值统一封装到api规范中
			IHttpResult objectIHttpResult = DefaultHttpResult.successWithData(body);
			if (body instanceof String) {
				// String需要特殊处理，SpringMVC会根据返回接口参数为对象时将当前返回值当成String处理，会出现强转异常
				try {
					//jsonManager对象可以处理为单例
					return new ObjectMapper().writeValueAsString(objectIHttpResult);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
					throw new RuntimeException("Conversion string result exception");
				}
			}
			return objectIHttpResult;
		}
	}
}
