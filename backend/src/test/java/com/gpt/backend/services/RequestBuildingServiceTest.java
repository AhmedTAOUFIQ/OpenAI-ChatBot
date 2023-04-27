package com.gpt.backend.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.gpt.backend.entities.ChatSession;
import com.gpt.backend.entities.QuestionAnswerPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class RequestBuildingServiceTest {
    @Autowired
    private RequestBuildingServiceInterface requestBuildingService;



    @Test
    public void testPromptBuilder() {
        ChatSession session = new ChatSession("0000");
        List<QuestionAnswerPair> questionAnswerPairs = new ArrayList<>();
        QuestionAnswerPair pair1 = new QuestionAnswerPair("Where is Morocco?", "Morocco is in Africa","0000");
        QuestionAnswerPair pair2 = new QuestionAnswerPair("What countries does it have borders with?", "It has borders with Algeria and Mauritania","0000");
        questionAnswerPairs.add(pair1);
        questionAnswerPairs.add(pair2);
        session.setQuestionAnswerPairs(questionAnswerPairs);
        String question = "What is its capital?";
        String expectedAnswer = "Where is Morocco? \n Morocco is in Africa \n What countries does it have borders with? \n It has borders with Algeria and Mauritania \n What is its capital?";
        String actual = requestBuildingService.promptBuilder(session, question);
        assertEquals(expectedAnswer, actual);
    }

    @Test
    public void testPromptBuilderNewSession() {
        ChatSession session = new ChatSession("0000");
        String question = "What is the capital of France?";
        String expectedAnswer = "What is the capital of France?";
        String actual = requestBuildingService.promptBuilder(session, question);
        assertEquals(expectedAnswer, actual);
    }

}