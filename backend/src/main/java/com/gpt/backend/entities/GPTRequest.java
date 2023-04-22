package com.gpt.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data  @AllArgsConstructor
@NoArgsConstructor
@Document
public class GPTRequest {
    //@Id
    //String Id;
    String model; String prompt; double temperature; int max_tokens;

    public GPTRequest(String prompt){
        this.prompt = prompt;
        this.model="text-davinci-003";
        this.temperature=0.8;
        this.max_tokens=100;
    }
}
