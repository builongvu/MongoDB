package com.example.mongodb.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "author")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {

    @Id
    String id;
//    @Indexed
    String name;
    int age;
//    @DBRef
//    @Field(name = "listBook")
    List<Book> books = new ArrayList<>();

    @PersistenceCreator
    public Author(String id, String name, @Value("#root.age ?: 1") int age, List<Book> books) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.books = books;
    }
}
