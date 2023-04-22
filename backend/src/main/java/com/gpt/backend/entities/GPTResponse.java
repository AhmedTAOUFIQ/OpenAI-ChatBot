package com.gpt.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class GPTResponse implements Serializable {
    @Id
    String id;
    @Transient
    String object;
    @Transient
    String created;
    @Transient
    String model;

    @JsonProperty("usage")
    Usage usage;
    @JsonProperty("choices")
    List<choice> choices;

    public String Answer() {
        if (choices == null || choices.isEmpty())
            return "Couldn't get an answer from the Open Ai Platform";
        return choices.get(0).text;
    }
    @Data
    static class   choice {
        String text;
        int index;
    }
    @Data
    static class Usage{
        int total_tokens; int prompt_tokens; int completion_tokens;
    }
}