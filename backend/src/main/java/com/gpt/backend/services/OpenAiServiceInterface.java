package com.gpt.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gpt.backend.DTO.GPTResponseDTO;
import org.apache.tomcat.util.json.ParseException;

import java.io.IOException;
import java.net.http.HttpRequest;

public interface OpenAiServiceInterface {
    GPTResponseDTO chatWithGpt(String message) throws IOException, InterruptedException, ParseException;

    HttpRequest.BodyPublisher chatMessageAsPostBody(String message) throws JsonProcessingException;

    String completionBuilder(String message) throws JsonProcessingException;
}
