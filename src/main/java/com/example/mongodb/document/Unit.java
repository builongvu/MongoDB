package com.example.mongodb.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@Document(collection = "units")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Unit {

    @Id
    String id;
    String name;
    @DBRef
    Set<Employee> employees;

}
