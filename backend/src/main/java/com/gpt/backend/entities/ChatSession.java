package com.gpt.backend.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data @Document

public class ChatSession {
    @Id
    private String sessionId;

    private String subject;
    private List<QuestionAnswerPair> questionAnswerPairs;

    public ChatSession(String sessionId) {
        this.sessionId = sessionId;
        this.subject = "";
        this.questionAnswerPairs = new ArrayList<>();
    }

    public void addQuestionAnswerPair(String question, String answer) {
        this.questionAnswerPairs.add(new QuestionAnswerPair(question, answer,this.sessionId));
    }
}
