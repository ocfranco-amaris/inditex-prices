package com.inditex.prices.exception.type;

import com.inditex.prices.exception.codes.ExceptionCodes;

public class InvalidFieldException extends BaseException {
	private static final long serialVersionUID = 1L;

	private final static Long id = ExceptionCodes.INVALID_FIELD;
	private final static String message = "Invalid field";

	private final String field;

	public InvalidFieldException(String field) {
		super(id, message);
		this.field = field;
	}

	public String getField() {
		return field;
	}

}
