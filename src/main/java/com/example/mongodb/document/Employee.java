package com.example.mongodb.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {

    @Id
    String id;
    String name;
    Address address;

}
