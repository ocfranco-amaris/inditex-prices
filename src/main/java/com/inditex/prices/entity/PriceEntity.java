package com.inditex.prices.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "prices")
@Data
public class PriceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "brand_id")
	private Long brandId;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "price_list")
	private Integer priceList;

	@Column(name = "product_id")
	private Long productId;

	private Integer priority;

	@Column(name = "price")
	private Double price;

	@Column(length = 3, name = "curr")
	private String currency;
}
