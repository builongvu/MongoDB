package com.example.mongodb.service;

import com.example.mongodb.entity.Author;
import org.springframework.stereotype.Service;

public interface AuthorService {

    Author createUseMongoRepository(Author author);

}
