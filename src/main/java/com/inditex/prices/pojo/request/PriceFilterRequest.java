package com.inditex.prices.pojo.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PriceFilterRequest {

	@ApiModelProperty(value = "Fecha de aplicación", dataType = "DateTime", example = "2020-01-01 00:00:00", required = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;

	@ApiModelProperty(value = "Identificador de producto", dataType = "Long", example = "1", required = true)
	private Long productId;

	@ApiModelProperty(value = "Identificador de cadena", dataType = "Long", example = "1", required = true)
	private Long brandId;

}
