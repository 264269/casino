package com.example.casino.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WinnerRepository extends MongoRepository<Winner, String> {
}
