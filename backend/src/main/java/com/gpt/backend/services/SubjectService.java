package com.gpt.backend.services;

import com.gpt.backend.DTO.GPTResponseDTO;
import com.gpt.backend.entities.ChatSession;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.io.IOException;
@Service
public class SubjectService implements SubjectServiceInterface{
    @Autowired
     private OpenAiServiceInterface openAiService;
    @Autowired
    private RequestBuildingServiceInterface requestBuilder;

    public void updateSessionSubject(ChatSession session) throws IOException, ParseException, InterruptedException {
        String generateSubject = "from all of this, generate a common subject , in not more than ten words, answer with the subject only";
        String prompt = requestBuilder.promptBuilder(session, generateSubject);
        GPTResponseDTO Subject = openAiService.chatWithGpt(prompt);
        session.setSubject(Subject.Answer().trim());
    }
}
