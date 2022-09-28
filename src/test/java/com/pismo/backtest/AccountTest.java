package com.pismo.backtest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.backtest.model.DTO.AccountsDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountTest {

	private static final String DOCUMENT_NUMBER = "31198766824";
	private static final String URL = "/api/accounts";
	private static final String CONTENT_TYPE = "application/json";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void mandatoryFilds() throws JsonProcessingException, Exception {
		AccountsDTO accountsDTO = new AccountsDTO();
		mockMvc.perform(post(URL, 42L).contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(accountsDTO)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]", equalTo("004 :Account field Document_Number is mandatory!")));
	}

	@Test
	public void createAccounts() throws JsonProcessingException, Exception {
		AccountsDTO accountsDTO = new AccountsDTO();
		accountsDTO.setDocument_Number(DOCUMENT_NUMBER);
		mockMvc.perform(post(URL, 42L).contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(accountsDTO)))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void notCreateDuplicateAccounts() throws JsonProcessingException, Exception {
		AccountsDTO accountsDTO = new AccountsDTO();
		accountsDTO.setDocument_Number(DOCUMENT_NUMBER);
		mockMvc.perform(post(URL, 42L).contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(accountsDTO)))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors[0]",
						equalTo("005 :Account found - possibly the account has already been created.")));
	}

}
