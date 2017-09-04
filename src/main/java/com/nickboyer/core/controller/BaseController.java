/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年8月29日 上午10:42:11
 */
package com.nickboyer.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nickboyer.core.biz.BizException;
import com.nickboyer.core.common.Result;
import com.nickboyer.core.common.ResultCode;

/**
 * @title
 * @description
 * @since JDK1.8
 */
public class BaseController {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@ExceptionHandler(Exception.class)
	protected @ResponseBody Object exp(HttpServletRequest request, Exception ex) {
		String requestURI = request.getRequestURI();
		Result result = new Result();
		try {
			result.setReqChid(request.getParameter("reqChid"));
			result.setReqNo(request.getParameter("reqno"));
			result.setReqTime(request.getParameter("reqTime"));
		} catch (Exception e) {
			logger.error("{}传输报文参数异常..", new Object[] { requestURI }, ex);
			result.setCode(ResultCode.ER9999);
			result.setMsg(ResultCode.ER9999_MSG);
		}
		// if (ex instanceof JSONException) {
		// logger.error("{}传输报文格式异常..", new Object[] { requestURI }, ex);
		// result.setCode(ResultCode.ER9998);
		// result.setMsg(ResultCode.ER9998_MSG);
		// }
		// else
		if (ex instanceof NullPointerException) {
			logger.error("{}传输报文参数空指针异常..", new Object[] { requestURI }, ex);
			result.setCode(ResultCode.ER9998);
			result.setMsg(ResultCode.ER9998_MSG);
		} else if (ex instanceof NumberFormatException) {
			logger.error("{}传输报文参数异常..", new Object[] { requestURI }, ex);
			result.setCode(ResultCode.ER9998);
			result.setMsg(ResultCode.ER9998_MSG);
		} else if (ex instanceof BizException) {
			BizException bizEx = (BizException) ex;
			logger.error("{}业务层异常..", new Object[] { requestURI }, ex);
			result.setCode(bizEx.messageCode);
			result.setMsg(bizEx.getMessage());
		} else {
			logger.error("{}应用层异常..", new Object[] { requestURI }, ex);
			result.setCode(ResultCode.ER9999);
			result.setMsg(ResultCode.ER9999_MSG);
		}
		try {
			logger.debug("return value:" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
		} catch (JsonProcessingException e) {
			logger.error("", e);
		}
		return result;
	}

}
