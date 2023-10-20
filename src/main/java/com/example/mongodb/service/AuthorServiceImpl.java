package com.example.mongodb.service;

import com.example.mongodb.entity.Author;
import com.example.mongodb.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAllUseMongoRepository() {
        return authorRepository.findAll();
    }

    @Override
    public Author createUseMongoRepository(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteUseMongoRepository(String id) {
        authorRepository.deleteById(id);
    }

}
