package com.gpt.backend.services;

import com.gpt.backend.DTO.GPTRequestDTO;
import com.gpt.backend.DTO.GPTResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Service
public class OpenAiService implements OpenAiServiceInterface {
    @Value("${openai_api_key}")
    String myOpenAiKey;
    private HttpClient client = HttpClient.newHttpClient();
    @Autowired
    private ObjectMapper jsonMapper;
    public String completionBuilder(String message) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(new GPTRequestDTO (message));
    }

       public HttpRequest.BodyPublisher chatMessageAsPostBody (String message) throws JsonProcessingException {
        var completion = new GPTRequestDTO(message) ;

        return HttpRequest.BodyPublishers.ofString(completionBuilder(message));
    }
    private final URI OPENAI_URI= URI.create("https://api.openai.com/v1/completions");
    // sending the PostRequest to OpenAi and getting the answer

    public  GPTResponseDTO chatWithGpt(String message) throws IOException, InterruptedException, ParseException {
        // Building the HTTP GPTRequest
        var theRequest = HttpRequest.newBuilder()
                .uri(OPENAI_URI)
                .headers("CONTENT-TYPE", APPLICATION_JSON_VALUE)
                .headers("AUTHORIZATION", "Bearer "+ myOpenAiKey)
                .POST(chatMessageAsPostBody(message))
                .build();

        // Getting the response body of the HTTP GPTRequest
        var responseBody = client.send(theRequest, HttpResponse.BodyHandlers.ofString()).body();

        // mapp the response body to the GPTResponse Class
        var Response = jsonMapper.addMixIn(List.class, ObjectListMixIn.class).readValue(responseBody, GPTResponseDTO.class);
        return Response;


    }
        // Adding the ObjectListMixIn in my Object Mapper
        @JsonFormat(shape = JsonFormat.Shape.ARRAY)
        private abstract class ObjectListMixIn<T> {
            ObjectListMixIn(@JsonProperty("choices") List<T> choices) {}
        }
}
