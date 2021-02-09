package com.inditex.prices.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.inditex.prices.entity.PriceEntity;
import com.inditex.prices.entity.PriceEntity_;
import com.inditex.prices.exception.type.BaseException;
import com.inditex.prices.pojo.request.PriceFilterRequest;
import com.inditex.prices.pojo.response.PriceResponse;
import com.inditex.prices.repository.PriceRepository;
import com.inditex.prices.utils.ConverterUtils;
import com.inditex.prices.utils.ValidationUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class PriceService {
	private final PriceRepository priceRepository;
	private final ValidationUtils validationUtils;
	private final ConverterUtils converterUtils;

	public PriceResponse findPriceWithFilter(final PriceFilterRequest filter) throws BaseException {
		log.info("Started process findPriceWithFilter with filter [{}]", filter);
		validationUtils.validatePriceFilterRequest(filter);

		final Specification<PriceEntity> specification = (Root<PriceEntity> root, CriteriaQuery<?> criteriaQuery,
				CriteriaBuilder criteriaBuilder) -> {
			final List<Predicate> predicates = new ArrayList<>();

			predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.brandId), filter.getBrandId()));

			predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.productId), filter.getProductId()));

			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PriceEntity_.endDate), filter.getDate()));

			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PriceEntity_.startDate), filter.getDate()));

			return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
		};

		PriceEntity priceEntity = priceRepository
				.findAll(specification, PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority"))).stream()
				.findFirst().orElse(null);
		if (priceEntity == null) {
			log.info("Data not found with filter [{}] ", filter);
			return null;
		}
		log.debug("Starting process to convert priceEntity [{}] to response", filter);
		final PriceResponse priceResponse = converterUtils.priceEntityToResponse(priceEntity);
		priceResponse.setDateTime(filter.getDate());

		log.info("Ended process findPriceWithFilter price response [{}]", priceResponse);
		return priceResponse;
	}
}
