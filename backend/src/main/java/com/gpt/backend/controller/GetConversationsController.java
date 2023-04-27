package com.gpt.backend.controller;

import com.gpt.backend.entities.QuestionAnswerPair;
import com.gpt.backend.repository.ChatSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetConversationsController {
    @Autowired
    ChatSessionRepository chatSessionRepository;

    @GetMapping("/sessions/{sessionId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<QuestionAnswerPair> createSession(@PathVariable String sessionId) {
        if (sessionId.equalsIgnoreCase("newsession")){
            return new ArrayList<>();
        }
        else {
            System.out.println(sessionId +"old one ");
            return chatSessionRepository.getChatSessionBySessionId(sessionId).getQuestionAnswerPairs();}
    }
}
