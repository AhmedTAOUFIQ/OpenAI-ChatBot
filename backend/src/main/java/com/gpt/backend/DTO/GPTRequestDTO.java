package com.gpt.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data  @AllArgsConstructor
@NoArgsConstructor
public class GPTRequestDTO {
    String model; String prompt; double temperature; int max_tokens;

    public GPTRequestDTO(String prompt){
        this.prompt = prompt;
        this.model="text-davinci-003";
        this.temperature=0.8;
        this.max_tokens=100;
    }
}
