package com.gpt.backend.repository;

import com.gpt.backend.entities.GPTRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestRepository extends MongoRepository <GPTRequest, String>  {
}
