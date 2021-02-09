package com.inditex.prices.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inditex.prices.exception.codes.ExceptionCodes;
import com.inditex.prices.exception.type.MissingFieldException;
import com.inditex.prices.pojo.response.PriceResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PriceControllerTest {
	private static final String API_PRICES = "/price";
	private final static Double TEST1 = 35.50;
	private final static Double TEST2 = 25.45;
	private final static Double TEST3 = 35.50;
	private final static Double TEST4 = 30.50;
	private final static Double TEST5 = 38.95;

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	public void getPrice_whenAllFiltersAreSettledAndFoundData() {
		ResponseEntity<PriceResponse> response = testRestTemplate.exchange(getUrl(1L, 35455L, "2020-06-14 12:00:00"),
				HttpMethod.GET, null, new ParameterizedTypeReference<PriceResponse>() {
				});
		assertNotNull(response);
	}

	@Test
	public void getPrice_whenAllFiltersAreSettledAndFoundNotData() {
		ResponseEntity<PriceResponse> response = testRestTemplate.exchange(getUrl(1L, 35455L, "2020-06-12 12:00:00"),
				HttpMethod.GET, null, new ParameterizedTypeReference<PriceResponse>() {
				});

		assertThat(response.getBody()).isEqualTo(null);
	}

	@Test
	public void getPrice_whenSomeFieldInFiltersIsNotSettled() {
		ResponseEntity<MissingFieldException> response = testRestTemplate.exchange(getUrl(1L, 35455L, null),
				HttpMethod.GET, null, new ParameterizedTypeReference<MissingFieldException>() {
				});
		assertThat(response.getBody().getId()).isEqualTo(ExceptionCodes.MISSING_FIELD);
	}

	@Test
	public void getPrice_withDataForTest1() {
		ResponseEntity<PriceResponse> response = testRestTemplate.exchange(getUrl(1L, 35455L, "2020-06-14 10:00:00"),
				HttpMethod.GET, null, new ParameterizedTypeReference<PriceResponse>() {
				});
		assertNotNull(response.getBody());
		assertThat(response.getBody().getPrice()).isEqualTo(TEST1);
	}

	@Test
	public void getPrice_withDataForTest2() {
		ResponseEntity<PriceResponse> response = testRestTemplate.exchange(getUrl(1L, 35455L, "2020-06-14 16:00:00"),
				HttpMethod.GET, null, new ParameterizedTypeReference<PriceResponse>() {
				});
		assertNotNull(response.getBody());
		assertThat(response.getBody().getPrice()).isEqualTo(TEST2);
	}

	@Test
	public void getPrice_withDataForTest3() {
		ResponseEntity<PriceResponse> response = testRestTemplate.exchange(getUrl(1L, 35455L, "2020-06-14 21:00:00"),
				HttpMethod.GET, null, new ParameterizedTypeReference<PriceResponse>() {
				});
		assertNotNull(response.getBody());
		assertThat(response.getBody().getPrice()).isEqualTo(TEST3);
	}

	@Test
	public void getPrice_withDataForTest4() {
		ResponseEntity<PriceResponse> response = testRestTemplate.exchange(getUrl(1L, 35455L, "2020-06-15 10:00:00"),
				HttpMethod.GET, null, new ParameterizedTypeReference<PriceResponse>() {
				});
		assertNotNull(response.getBody());
		assertThat(response.getBody().getPrice()).isEqualTo(TEST4);
	}

	@Test
	public void getPrice_withDataForTest5() {
		ResponseEntity<PriceResponse> response = testRestTemplate.exchange(getUrl(1L, 35455L, "2020-06-16 21:00:00"),
				HttpMethod.GET, null, new ParameterizedTypeReference<PriceResponse>() {
				});
		assertNotNull(response.getBody());
		assertThat(response.getBody().getPrice()).isEqualTo(TEST5);
	}

	private String getUrl(final Long brandId, final Long productId, final String date) {
		String url = API_PRICES + "?";
		if (brandId != null) {
			url = String.format("%sbrandId=%s&", url, brandId);
		}
		if (productId != null) {
			url = String.format("%sproductId=%s&", url, productId);
		}
		if (date != null) {
			url = String.format("%sdate=%s&", url, date);
		}
		return url;
	}
}
