package com.example.mongodb.service;

import com.example.mongodb.document.Author;
import com.example.mongodb.dto.AuthorDto;

import javax.swing.text.Document;
import java.util.List;

public interface AuthorService {

    List<Author> getAllUseMongoRepository();

    Author createUseMongoRepository(Author author);

    Author updateUseMongoRepository(Author author);

    void deleteUseMongoRepository(String id);

    //custom
    List<Author> getByAgeLessThan(int age);

    List<Author> getByNameAndAge(String name, int age);

    List<Author> getByNameOrAge(String name, int age);

    //----------------------------------MongoTemplate---------------------------------------------
    List<Author> getAllUseMongoTemplate();

    Author createUseMongoTemplate(Author author);

    Author updateUseMongoTemplate(Author author);

    void deleteUseMongoTemplate(String id);

    List<Author> customQuery();

    List<Author> customQuery2();

    List<Author> search(String name, Integer age, String bookName);

    // Aggregation
    AuthorDto getAuthorWithMostBooks();

    AuthorDto getAverageAgeOfAuthor();

    //-----------------------------------MongoClient----------------------------------------------
    List<Author> getAllUseMongoClient();

    String createUseMongoClient(Author author);

    String updateUseMongoClient(Author author);

    String deleteUseMongoClient(String id);

}
