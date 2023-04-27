package com.gpt.backend.controller;

import com.gpt.backend.DTO.GPTResponseDTO;
import com.gpt.backend.entities.ChatSession;
import com.gpt.backend.repository.ChatSessionRepository;
import com.gpt.backend.services.OpenAiServiceInterface;
import com.gpt.backend.services.RequestBuildingServiceInterface;
import com.gpt.backend.services.SubjectServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ChatController {
    @Autowired
    RequestBuildingServiceInterface requestBuilder;
    private Map<String, ChatSession> sessions;

    public ChatController() {
        this.sessions = new HashMap<>();
    }
    @Autowired
    SubjectServiceInterface subjectService;
    @Autowired
    ChatSessionRepository chatSessionRepository;
    @Autowired
    private OpenAiServiceInterface openAiService;


    @PostMapping(path = "/sessions/{sessionId}/qa")
    @CrossOrigin(origins = "http://localhost:3000")
    public ChatSession chat(@PathVariable String sessionId, @RequestBody String question) throws Exception {
        // Creating new session if a new question has been asked :
        if (sessionId.equalsIgnoreCase("newsession")){
        sessionId = UUID.randomUUID().toString();
        ChatSession session = new ChatSession(sessionId);
        chatSessionRepository.save(session);
        this.sessions.put(sessionId, session);}

        // getting the current session from the map in the controller
        ChatSession session = chatSessionRepository.getChatSessionBySessionId(sessionId);
        if (session == null) {
            return null;
        }
        String request = requestBuilder.promptBuilder(session,question);
        GPTResponseDTO GPTResponseDTO = openAiService.chatWithGpt(request);
        session.addQuestionAnswerPair(question, GPTResponseDTO.Answer().trim());
        chatSessionRepository.save(session);
        subjectService.updateSessionSubject(session);
        chatSessionRepository.save(session);
        return session;
    }


}
