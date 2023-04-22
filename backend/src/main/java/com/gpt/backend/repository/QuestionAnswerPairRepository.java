package com.gpt.backend.repository;

import com.gpt.backend.entities.QuestionAnswerPair;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionAnswerPairRepository extends MongoRepository<QuestionAnswerPair,String> {
}
