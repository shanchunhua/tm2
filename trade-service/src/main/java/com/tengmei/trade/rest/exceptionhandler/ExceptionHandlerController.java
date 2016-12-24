package com.tengmei.trade.rest.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengmei.trade.rest.RestResult;

@Controller
@ControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {
		RestResult<String> restResult = new RestResult<String>(ex.getMessage());
		restResult.setSuccess(false);
		return new ResponseEntity<Object>(restResult, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
