package com.inditex.prices.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.inditex.prices.exception.type.BaseException;

import lombok.Data;

@Data
public class ExceptionView {
	private final Long id;
	private final LocalDateTime dateTime;
	private final String description;
	private final Map<String, Object> details;

	public ExceptionView(final BaseException exception) {
		this(exception.getId(), exception.getMessage());
	}

	public ExceptionView(final Long id, final String description) {
		this(id, description, null);
	}

	public ExceptionView(final Long id, final String description, final Map<String, Object> details) {
		this.id = id;
		this.dateTime = LocalDateTime.now();
		this.description = description;
		if (details == null) {
			this.details = new HashMap<>();
		} else {
			this.details = details;
		}
	}

	public void addDetails(final String key, final Object value) {
		details.put(key, value);
	}

}
