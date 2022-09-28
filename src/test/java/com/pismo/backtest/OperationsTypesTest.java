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
import com.pismo.backtest.model.DTO.OperationsTypesDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OperationsTypesTest {

	private static final String DESCRIPTION = "COMPRA A VISTA";
	private static final String URL = "/api/operationstypes";
	private static final String CONTENT_TYPE = "application/json";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void mandatoryFilds() throws JsonProcessingException, Exception {
		OperationsTypesDTO operationsTypesDTO = new OperationsTypesDTO();
		mockMvc.perform(
				post(URL, 42L).contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(operationsTypesDTO)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]", equalTo("006 :OperationsTypes field Description is mandatory!")));

	}

	@Test
	public void createOperationsTypes() throws JsonProcessingException, Exception {
		OperationsTypesDTO operationsTypesDTO = new OperationsTypesDTO();
		operationsTypesDTO.setDescription(DESCRIPTION);
		mockMvc.perform(
				post(URL, 42L).contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(operationsTypesDTO)))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.status().isCreated());
	}

}
