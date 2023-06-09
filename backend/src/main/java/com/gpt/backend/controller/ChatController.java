package com.gpt.backend.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpt.backend.entities.GPTRequest;
import com.gpt.backend.entities.GPTResponse;
import com.gpt.backend.entities.ChatSession;
import com.gpt.backend.entities.QuestionAnswerPair;
import com.gpt.backend.repository.ChatSessionRepository;
import com.gpt.backend.repository.RequestRepository;
import com.gpt.backend.repository.ResponseRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ChatController {
    private Map<String, ChatSession> sessions;
    public ChatController() {
        this.sessions = new HashMap<>();
    }
    @Autowired
    ChatSessionRepository chatSessionRepository;

    @GetMapping("/sessions")
    public ResponseEntity<String> createSession() {
        ChatSession session = new ChatSession("000002");
        chatSessionRepository.save(session);
        this.sessions.put("1234", session);
        System.out.println(session);
        return new ResponseEntity<>("1234", HttpStatus.CREATED);
    }

    @PostMapping(path = "/sessions/{sessionId}/qa")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<QuestionAnswerPair> chat(@PathVariable String sessionId, @RequestBody String question) throws Exception {
        // getting the current session from the map in the controller
        ChatSession session = chatSessionRepository.getChatSessionBySessionId(sessionId);
        if (session == null) {
            return null;
        }

        GPTResponse GPTResponse = chatWithGpt(promptBuilder(session,question));
        System.out.println(promptBuilder(session,question));
        System.out.println("--------------------------------------------");
        responseRepository.save(GPTResponse);
        requestRepository.save(new GPTRequest(question));
        System.out.println(GPTResponse.Answer());
        System.out.println("---------------------------------------------");
        session.addQuestionAnswerPair(question,GPTResponse.Answer().trim());
        chatSessionRepository.save(session);
        System.out.println(session.getQuestionAnswerPairs());
        System.out.println("********************************************");
        return session.getQuestionAnswerPairs();
    }



    // Instantiating the objects
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private ObjectMapper jsonMapper;

    @Value("${openai.api_key}")
    private String myOpenAiKey;
    private HttpClient client = HttpClient.newHttpClient();
    private static final URI OPENAI_URI= URI.create("https://api.openai.com/v1/completions");


    //Converting the message to PostBody with default Params.
    private HttpRequest.BodyPublisher chatMessageAsPostBody (String message) throws JsonProcessingException {
        var completion = new GPTRequest(message) ;
        return HttpRequest.BodyPublishers.ofString(jsonMapper.writeValueAsString(completion));
    }
    // sending the PostRequest to OpenAi and getting the answer

    private GPTResponse chatWithGpt(String message) throws Exception {
        // Building the HTTP GPTRequest
        var theRequest = HttpRequest.newBuilder()
                .uri(OPENAI_URI)
                .headers("CONTENT-TYPE", APPLICATION_JSON_VALUE)
                .headers("AUTHORIZATION", "Bearer "+ myOpenAiKey)
                .POST(chatMessageAsPostBody(message))
                .build();

        // Getting the response body of the HTTP GPTRequest
        var responseBody = client.send(theRequest, HttpResponse.BodyHandlers.ofString()).body();
        var parsedResponse =  new JSONParser(responseBody).parse();

        // mapp the response body to the GPTResponse Class
        var Response = jsonMapper.addMixIn(List.class, ObjectListMixIn.class).readValue(responseBody, GPTResponse.class);
        return Response;


    }
    // Adding the ObjectListMixIn in my Object Mapper
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private abstract class ObjectListMixIn<T> {
        ObjectListMixIn(@JsonProperty("choices") List<T> choices) {}
    }


    // method to generate a unique session ID
    private String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    private String promptBuilder(ChatSession session, String question){
        StringBuilder builder = new StringBuilder();
    if (session.getQuestionAnswerPairs().size()!=0){
            if (session.getQuestionAnswerPairs().size()<20) {
                for (int i = 0; i < session.getQuestionAnswerPairs().size(); i++) {
                    builder.append("Q : ");
                    builder.append(session.getQuestionAnswerPairs().get(i).getQuestion() + ", ");
                    builder.append("A : ");
                    builder.append(session.getQuestionAnswerPairs().get(i).getAnswer() + ", ");
                    builder.append("Q : ");
                    builder.append(question);
                    builder.append("  give the answer directly, without taking the Q: A: format in consideration");
                }
            } else {
                for (int i = session.getQuestionAnswerPairs().size()-20; i < session.getQuestionAnswerPairs().size(); i++) {
                    builder.append("Q : ");
                    builder.append(session.getQuestionAnswerPairs().get(i).getQuestion() + ", ");
                    builder.append("A : ");
                    builder.append(session.getQuestionAnswerPairs().get(i).getAnswer() + ", ");
                    builder.append("Q : ");
                    builder.append(question);
                    builder.append("  give the answer directly, without taking the Q: A: format in consideration");
                }
            }
    }
    else { builder.append(question);
    }
        return builder.toString();
    }

}
