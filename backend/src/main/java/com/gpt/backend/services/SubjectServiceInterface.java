package com.gpt.backend.services;

import com.gpt.backend.entities.ChatSession;
import org.apache.tomcat.util.json.ParseException;

import java.io.IOException;

public interface SubjectServiceInterface {
     void updateSessionSubject(ChatSession session) throws IOException, ParseException, InterruptedException;
}
