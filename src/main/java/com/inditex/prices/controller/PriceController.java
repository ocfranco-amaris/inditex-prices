package com.inditex.prices.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.prices.exception.type.BaseException;
import com.inditex.prices.pojo.request.PriceFilterRequest;
import com.inditex.prices.pojo.response.PriceResponse;
import com.inditex.prices.service.PriceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/price")
@Api(value = "API Prices", produces = "application/json")
@AllArgsConstructor
public class PriceController {
	private final PriceService priceService;

	@GetMapping
	@ApiOperation(value = "Encuentra un precio luego de realizar el filtro, si uno de los filtros no se envia lanza una exception", notes = "Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.")
	public ResponseEntity<PriceResponse> findPriceWithFilter(final PriceFilterRequest filter) throws BaseException {
		return ResponseEntity.ok(priceService.findPriceWithFilter(filter));
	}
}
