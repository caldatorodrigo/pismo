package com.pismo.backtest.test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pismo.backtest.model.TransactionsModel;
import com.pismo.backtest.repository.TransactionsRepository;

import io.swagger.v3.core.util.Json;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransactionTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TransactionsRepository transactionsRepository;

	@Test
	public void mandatoryTest() {
		TransactionsModel transactionsModel = new TransactionsModel();
		assertThatThrownBy(() -> transactionsRepository.save(transactionsModel))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	public void creatingTransactionsTest() {
		HttpEntity<TransactionsModel> request = new HttpEntity<>(new TransactionsModel((long)1, (long)1, BigDecimal.valueOf(-50.00)));
		ResponseEntity<TransactionsModel> response = restTemplate.exchange(
				"http://localhost:" + port + "/api/transactions", HttpMethod.POST, request, TransactionsModel.class);

		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

		TransactionsModel transactionsModel = response.getBody();
		Json.prettyPrint(transactionsModel);
		
		assertThat(transactionsModel, notNullValue());
		assertThat(transactionsModel.getAccount_ID(), is(1L));
		assertThat(transactionsModel.getOperationType_ID(), is(1L));
		assertThat(transactionsModel.getAmount(), is(BigDecimal.valueOf(-50.0)));
	}

}
