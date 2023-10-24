package com.example.mongodb.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@RedisHash("student")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student implements Serializable {
    int id;
    String name;
}
