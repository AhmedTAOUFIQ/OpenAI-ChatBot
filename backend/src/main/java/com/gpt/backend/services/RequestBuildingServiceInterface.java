package com.gpt.backend.services;

import com.gpt.backend.entities.ChatSession;

public interface RequestBuildingServiceInterface {
    String promptBuilder(ChatSession session, String question);
}
