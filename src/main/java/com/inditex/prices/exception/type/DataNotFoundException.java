package com.inditex.prices.exception.type;

import com.inditex.prices.exception.codes.ExceptionCodes;

public class DataNotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;

	private final static Long id = ExceptionCodes.DATA_NOT_FOUND;
	private final static String message = "Data not found";

	private final String data;

	public DataNotFoundException(String data) {
		super(id, message);
		this.data = data;
	}

	public String getData() {
		return data;
	}

}
