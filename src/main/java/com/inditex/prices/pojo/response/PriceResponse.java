package com.inditex.prices.pojo.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PriceResponse {
	private Long productId;
	private Long brandId;
	private Integer priceList;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;
	private Double price;

}
