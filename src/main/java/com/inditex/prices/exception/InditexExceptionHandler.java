package com.inditex.prices.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inditex.prices.exception.type.DataNotFoundException;
import com.inditex.prices.exception.type.InvalidFieldException;
import com.inditex.prices.exception.type.MissingFieldException;

@ControllerAdvice
public class InditexExceptionHandler {
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseBody
	public ExceptionView handlerError(HttpServletRequest request, DataNotFoundException exception) {
		final ExceptionView view = new ExceptionView(exception);
		view.addDetails("data", exception.getData());
		return view;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidFieldException.class)
	@ResponseBody
	public ExceptionView handlerError(HttpServletRequest request, InvalidFieldException exception) {
		final ExceptionView view = new ExceptionView(exception);
		view.addDetails("field", exception.getField());
		return view;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(MissingFieldException.class)
	@ResponseBody
	public ExceptionView handlerError(HttpServletRequest request, MissingFieldException exception) {
		final ExceptionView view = new ExceptionView(exception);
		view.addDetails("field", exception.getField());
		return view;
	}

}
