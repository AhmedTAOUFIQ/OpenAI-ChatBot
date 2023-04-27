package com.gpt.backend.controller;

import com.gpt.backend.entities.ChatSession;
import com.gpt.backend.repository.ChatSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetSessionsController {
    @Autowired
    ChatSessionRepository chatSessionRepository;

    @GetMapping(path="/sessions/getsessions")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<ChatSession> chatSessions() {
        return chatSessionRepository.findAll();
    }
}
