package com.example.mongodb;

import com.example.mongodb.entity.Author;
import com.example.mongodb.entity.Book;
import com.example.mongodb.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MongoDbApplication implements CommandLineRunner {

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongoDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		authorRepository.save(Author.builder().name("Van A").books(List.of(Book.builder().name("GDCD").build())).build());
	}
}
