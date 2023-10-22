package com.example.mongodb.dto;

import com.example.mongodb.document.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto {

    String id;
    String name;
    int age;
    List<Book> books = new ArrayList<>();
    int totalBooks;
    double avgAge;

}
