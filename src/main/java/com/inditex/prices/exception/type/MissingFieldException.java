package com.inditex.prices.exception.type;

import com.inditex.prices.exception.codes.ExceptionCodes;

public class MissingFieldException extends BaseException {
	private static final long serialVersionUID = 1L;

	private final static Long id = ExceptionCodes.MISSING_FIELD;
	private final static String message = "Missing field";

	private final String field;

	public MissingFieldException(String field) {
		super(id, message);
		this.field = field;
	}

	public String getField() {
		return field;
	}

}
