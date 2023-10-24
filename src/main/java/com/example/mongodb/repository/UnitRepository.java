package com.example.mongodb.repository;

import com.example.mongodb.document.Unit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends MongoRepository<Unit, String> {
}
