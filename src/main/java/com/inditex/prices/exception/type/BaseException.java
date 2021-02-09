package com.inditex.prices.exception.type;

import lombok.Getter;

@Getter
public abstract class BaseException extends Exception {
	private static final long serialVersionUID = 1L;

	protected final String message;
	protected final Long id;

	public BaseException(Long id, String message) {
		super();
		this.message = message;
		this.id = id;
	}

}
