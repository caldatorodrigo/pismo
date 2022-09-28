package com.pismo.backtest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.backtest.model.DTO.TransactionsDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionTest {

	private static final Long ACCOUNT = 1L;
	private static final Long OPERATIONTYPE = 1L;
	private static final BigDecimal AMOUNT = new BigDecimal(-50.00);
	private static final String URL = "/api/transactions";
	private static final String CONTENT_TYPE = "application/json";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void mandatoryFilds() throws JsonProcessingException, Exception {
		TransactionsDTO transactionsDTO = new TransactionsDTO();
		mockMvc.perform(post(URL, 42L).contentType(CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(transactionsDTO))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]", equalTo("006 :Transaction field Account is mandatory!")))
				.andExpect(jsonPath("$.errors[1]", equalTo("007 :Transaction field OperationType_ID is mandatory!")))
				.andExpect(jsonPath("$.errors[2]", equalTo("008 :Transaction field OperationType_ID is mandatory!")));
	}

	@Test
	public void createAccounts() throws JsonProcessingException, Exception {
		TransactionsDTO transactionsDTO = new TransactionsDTO();
		transactionsDTO.setAccount_ID(ACCOUNT);
		transactionsDTO.setOperationType_ID(OPERATIONTYPE);
		transactionsDTO.setAmount(AMOUNT);
		mockMvc.perform(post(URL, 42L).contentType(CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(transactionsDTO))).andExpect(status().isCreated())
				.andDo(print()).andExpect(MockMvcResultMatchers.status().isCreated());
	}
}
