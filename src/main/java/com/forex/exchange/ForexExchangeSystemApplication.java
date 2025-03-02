package com.forex.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class ForexExchangeSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexExchangeSystemApplication.class, args);
	}
}

