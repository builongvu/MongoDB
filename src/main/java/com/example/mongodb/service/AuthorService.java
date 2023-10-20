package com.example.mongodb.service;

import com.example.mongodb.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {

    List<Author> getAllUseMongoRepository();

    Author createUseMongoRepository(Author author);

    void deleteUseMongoRepository(String id);

}
