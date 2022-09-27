package com.pismo.backtest.test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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

import com.pismo.backtest.model.AccountsModel;
import com.pismo.backtest.model.OperationsTypesModel;
import com.pismo.backtest.repository.OperationsTypesRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OperationsTypesTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private OperationsTypesRepository operationsTypesRepository;

	/** Fields Mandatory */
	@Test
	public void mandatoryTest() {
		OperationsTypesModel operationsTypesModel = new OperationsTypesModel("");
		assertThatThrownBy(() -> operationsTypesRepository.save(operationsTypesModel))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	public void creatingOperationsTypesTest() {
		HttpEntity<OperationsTypesModel> request = new HttpEntity<>(new OperationsTypesModel("COMPRA A VISTA"));
		ResponseEntity<OperationsTypesModel> response = restTemplate.exchange("http://localhost:" + port + "/api/operationstypes",
				HttpMethod.POST, request, OperationsTypesModel.class);

		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

		OperationsTypesModel operationsTypesModel = response.getBody();
		assertThat(operationsTypesModel, notNullValue());
		assertThat(operationsTypesModel.getDescription(), is("COMPRA A VISTA"));
	}


}
