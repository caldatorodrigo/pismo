package com.pismo.backtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@ComponentScan({ "com.pismo.backtest" })
@OpenAPIDefinition(info = @Info(title = "Pismo BackTest 3.0 API", version = "3.0", description = "Account and Transactions Information"))
public class BacktestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BacktestApplication.class, args);
	}

}
