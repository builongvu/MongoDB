package com.example.mongodb;

import com.example.mongodb.entity.Author;
import com.example.mongodb.entity.Book;
import com.example.mongodb.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorCommandLineRunner implements CommandLineRunner {

    private final AuthorService authorService;

    @Override
    public void run(String... args) throws Exception {
//        authorService.getAllUseMongoRepository().forEach(System.out::println);

//        Author author = Author.builder()
//                .name("Nguyen Van A")
//                .age(34)
//                .books(List.of(Book.builder().name("Toan").publishDate(new Date(System.currentTimeMillis())).build()))
//                .build();
//        authorService.createUseMongoRepository(author);

        authorService.deleteUseMongoRepository("653238006bafd24878bb9a0d");
    }

}
