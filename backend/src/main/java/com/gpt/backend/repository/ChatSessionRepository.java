package com.gpt.backend.repository;

import com.gpt.backend.entities.ChatSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatSessionRepository extends MongoRepository<ChatSession, String> {
    ChatSession getChatSessionBySessionId(String sessionID);
}
