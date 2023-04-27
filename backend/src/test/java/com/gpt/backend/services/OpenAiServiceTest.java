package com.gpt.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OpenAiServiceTest {
    @Autowired
    private ObjectMapper jsonMapper;
    @Autowired
    private OpenAiServiceInterface openAiService;


    @Test
    void testCompletionBuilder() throws JsonProcessingException {

        String message = "This is a question?";
        String actual = openAiService.completionBuilder(message);
        String expected =
                "{\"model\":\"text-davinci-003\",\"prompt\":\"This is a question?\",\"temperature\":0.8,\"max_tokens\":100}"
        ;

        assertEquals(expected, actual);
    }
}
