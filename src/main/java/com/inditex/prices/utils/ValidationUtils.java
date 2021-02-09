package com.inditex.prices.utils;

import org.springframework.stereotype.Component;

import com.inditex.prices.exception.type.MissingFieldException;
import com.inditex.prices.pojo.request.PriceFilterRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ValidationUtils {
	public void validatePriceFilterRequest(final PriceFilterRequest filter) throws MissingFieldException {
		log.debug("Starting Price Filter Request Validation with filter [{}] ", filter);

		if (filter.getBrandId() == null) {
			log.error("Missing field [{}] ", "brandId");
			throw new MissingFieldException("brandId");
		}

		if (filter.getProductId() == null) {
			log.error("Missing field [{}] ", "productId");
			throw new MissingFieldException("productId");
		}

		if (filter.getDate() == null) {
			log.error("Missing field [{}] ", "date");
			throw new MissingFieldException("date");
		}
	}
}
