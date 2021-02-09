package com.inditex.prices.utils;

import org.springframework.stereotype.Component;

import com.inditex.prices.entity.PriceEntity;
import com.inditex.prices.pojo.response.PriceResponse;

@Component
public class ConverterUtils {
	public PriceResponse priceEntityToResponse(final PriceEntity priceEntity) {
		final PriceResponse priceResponse = new PriceResponse();
		priceResponse.setBrandId(priceEntity.getBrandId());
		priceResponse.setPrice(priceEntity.getPrice());
		priceResponse.setPriceList(priceEntity.getPriceList());
		priceResponse.setProductId(priceEntity.getProductId());
		return priceResponse;
	}
}
