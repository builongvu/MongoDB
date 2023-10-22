package com.example.mongodb.controller;

import com.example.mongodb.document.Author;
import com.example.mongodb.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors/mongo-repository")
public class AuthorControllerUseMongoRepo {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAll() {
        return authorService.getAllUseMongoRepository();
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        return authorService.createUseMongoRepository(author);
    }

    @PutMapping
    public Author update(@RequestBody Author author) {
        return authorService.updateUseMongoRepository(author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        authorService.deleteUseMongoRepository(id);
    }

    //Custom
    @GetMapping("/findByAgeLessThan/{age}")
    public List<Author> getByAgeLessThan(@PathVariable int age) {
        return authorService.getByAgeLessThan(age);
    }

    @GetMapping("/findByNameAndAge/{name}/{age}")
    public List<Author> getByNameAndAge(@PathVariable String name, @PathVariable int age) {
        return authorService.getByNameAndAge(name, age);
    }

    @GetMapping("/findByNameOrAge/{name}/{age}")
    public List<Author> getByNameOrAge(@PathVariable String name, @PathVariable int age) {
        return authorService.getByNameOrAge(name, age);
    }

}
