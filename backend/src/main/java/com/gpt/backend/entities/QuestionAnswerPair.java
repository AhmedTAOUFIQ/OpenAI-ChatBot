package com.gpt.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @AllArgsConstructor
@Document
public class QuestionAnswerPair {
    String question;
    String answer;
    String sessionId;
}


