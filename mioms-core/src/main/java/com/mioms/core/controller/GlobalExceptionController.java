package com.mioms.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mioms.core.dto.ErrorMsgDto;
import com.mioms.core.exception.ControllerGenericException;
import com.mioms.core.exception.ServiceGenericException;


@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorMsgDto handleAllException(Exception ex) {
		ErrorMsgDto errorMsgDto = new ErrorMsgDto();
		errorMsgDto.setErrorCode("001");
		errorMsgDto.setErrorMsg("exception");
		return errorMsgDto;
	}

	@ExceptionHandler(ControllerGenericException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorMsgDto handleControllerException(ControllerGenericException ex) {
		ErrorMsgDto errorMsgDto = new ErrorMsgDto();
		errorMsgDto.setErrorCode( ex.getErrCode());
		errorMsgDto.setErrorMsg( ex.getErrMsg());
		return errorMsgDto;
	}

	
	@ExceptionHandler(ServiceGenericException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorMsgDto handleServiceGenericException(ServiceGenericException ex) {
		ErrorMsgDto errorMsgDto = new ErrorMsgDto();
		errorMsgDto.setErrorCode( ex.getErrCode());
		errorMsgDto.setErrorMsg( ex.getErrMsg());
		return errorMsgDto;
	}
	
}