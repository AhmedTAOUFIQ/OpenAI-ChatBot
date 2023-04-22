package com.gpt.backend.repository;

import com.gpt.backend.entities.GPTResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseRepository extends MongoRepository<GPTResponse, String> {
}
