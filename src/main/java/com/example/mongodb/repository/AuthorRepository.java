package com.example.mongodb.repository;

import com.example.mongodb.document.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findByAgeLessThan(int age);

//    @Query("{name: {$regex: /^V/}}")
    List<Author> findByNameStartsWith(String name);

    @Query("{name: ?0, age: {$gt: ?1}}")
    List<Author> findByNameAndAge(String name, int age);

    @Query("{$or: [{name: ?0}, {age: ?1}]}")
    List<Author> findByNameOrAge(String name, int age);

    @Query("{name: 'Van C', $or: [{age: {$lt: 50}}, {age: {$gt: 70}}]}")
    List<Author> findByNameAndAgeUseOr();

    @Query(value = "{'books.name': ?0}", fields = "{books: 0}")
    List<Author> findByBookName(String bookName);

    @Query("{}")
    Author updateAgeById();

}
