package com.gpt.backend.services;

import com.gpt.backend.entities.ChatSession;
import org.springframework.stereotype.Service;

@Service
public class RequestBuildingService implements RequestBuildingServiceInterface {
    public String promptBuilder(ChatSession session, String question){
        StringBuilder builder = new StringBuilder();
        if (session.getQuestionAnswerPairs().size()!=0){
            if (session.getQuestionAnswerPairs().size()<20) {
                for (int i = 0; i < session.getQuestionAnswerPairs().size(); i++) {
                    builder.append(session.getQuestionAnswerPairs().get(i).getQuestion() + " \n ");
                    builder.append(session.getQuestionAnswerPairs().get(i).getAnswer() + " \n ");
                    }
                builder.append(question);

            } else {
                for (int i = session.getQuestionAnswerPairs().size()-20; i < session.getQuestionAnswerPairs().size(); i++) {
                    builder.append(session.getQuestionAnswerPairs().get(i).getQuestion() + " \n ");
                    builder.append(session.getQuestionAnswerPairs().get(i).getAnswer() + " \n ");
                    }
                builder.append(question);

            }
        }
        else {builder.append(question);
        }
        return builder.toString();
    }
}
