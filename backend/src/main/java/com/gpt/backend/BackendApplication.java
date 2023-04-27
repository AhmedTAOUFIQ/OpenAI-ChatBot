package com.gpt.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpt.backend.DTO.GPTRequestDTO;
import com.gpt.backend.services.OpenAiServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args)  {
		SpringApplication.run(BackendApplication.class, args);


	}

}
