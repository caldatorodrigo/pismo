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
import com.pismo.backtest.repository.AccountsRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private AccountsRepository accountRepository;

	/** Fields Mandatory */
	@Test
	public void mandatoryTest() {
		AccountsModel accountsModel = new AccountsModel("");
		assertThatThrownBy(() -> accountRepository.save(accountsModel))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	public void creatingDocumentTest() {
		HttpEntity<AccountsModel> request = new HttpEntity<>(new AccountsModel("31198766824"));
		ResponseEntity<AccountsModel> response = restTemplate.exchange("http://localhost:" + port + "/api/accounts",
				HttpMethod.POST, request, AccountsModel.class);

		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

		AccountsModel accountsModel = response.getBody();
		assertThat(accountsModel, notNullValue());
		assertThat(accountsModel.getDocument_Number(), is("31198766824"));
	}

	@Test
	public void notCreateDuplicateDocumentTest() {
		HttpEntity<AccountsModel> request = new HttpEntity<>(new AccountsModel("31198766824"));
		ResponseEntity<AccountsModel> response = restTemplate.exchange("http://localhost:" + port + "/api/accounts",
				HttpMethod.POST, request, AccountsModel.class);

		assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
	}

}
