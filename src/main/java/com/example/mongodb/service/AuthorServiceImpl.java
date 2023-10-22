package com.example.mongodb.service;

import com.example.mongodb.document.Author;
import com.example.mongodb.document.Book;
import com.example.mongodb.dto.AuthorDto;
import com.example.mongodb.repository.AuthorRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    @Value("${spring.data.mongodb.database}")
    private String databaseEnv;
    private final AuthorRepository authorRepository;
    private final MongoTemplate mongoTemplate;
    private final MongoClient mongoClient;

    @Override
    public List<Author> getAllUseMongoRepository() {
        return authorRepository.findAll();
    }

    @Override
    public Author createUseMongoRepository(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateUseMongoRepository(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteUseMongoRepository(String id) {
        authorRepository.deleteById(id);
    }

    //------------------------------------Custom-----------------------------------------------
    @Override
    public List<Author> getByAgeLessThan(int age) {
        return authorRepository.findByAgeLessThan(age);
    }

    @Override
    public List<Author> getByNameAndAge(String name, int age) {
        return authorRepository.findByNameAndAge(name, age);
    }

    @Override
    public List<Author> getByNameOrAge(String name, int age) {
        return authorRepository.findByNameOrAge(name, age);
    }

    @Override
    public List<Author> getAllUseMongoTemplate() {
        return mongoTemplate.findAll(Author.class);
    }

    //--------------------------------------MongoTemplate------------------------------------------
    @Override
    public Author createUseMongoTemplate(Author author) {
        return mongoTemplate.save(author);
    }

    @Override
    public Author updateUseMongoTemplate(Author author) {
        return mongoTemplate.save(author);
    }

    @Override
    public void deleteUseMongoTemplate(String id) {
        Author author = mongoTemplate.findById(id, Author.class);
        mongoTemplate.remove(author);
    }

    @Override
    public List<Author> customQuery() {
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("name").is("Van A"),
                new Criteria().orOperator(
                        Criteria.where("age").lte(50),
                        Criteria.where("age").gte(70)
                )
        );
        Query query = new Query(criteria).limit(10).with(Sort.by(Sort.Order.asc("name")));
        return mongoTemplate.find(query, Author.class);
    }

    @Override
    public List<Author> customQuery2() {
        Query query = new Query();
        Criteria criteria = Criteria.where("books.name").regex("^V");
        query.addCriteria(criteria).fields().exclude("books");
        return mongoTemplate.find(query, Author.class);
    }

    @Override
    public List<Author> search(String name, Integer age, String bookName) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        if (StringUtils.hasText(name)) {
            criteriaList.add(Criteria.where("name").regex("^.*?"+name+".*?$"));
//            criteriaList.add(Criteria.where("name").is(name));
        }
        if (age != null) {
            criteriaList.add(Criteria.where("age").is(age));
        }
        if (StringUtils.hasText(bookName)) {
            criteriaList.add(Criteria.where("books.name").regex("^.*?"+bookName+".*?$"));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList));
        }

        query.fields().exclude("books");

        return mongoTemplate.find(query, Author.class);
    }

    // Aggregation
    @Override
    public AuthorDto getAuthorWithMostBooks() {
//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.unwind("books"),
//                Aggregation.group("id", "name", "age").count().as("totalBooks"),
//                Aggregation.sort(Sort.Direction.DESC, "totalBooks"),
//                Aggregation.limit(1)
//        );
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("name", "age", "books")
                        .andExpression("size(books)").as("totalBooks"),
                Aggregation.sort(Sort.Direction.DESC, "totalBooks"),
                Aggregation.limit(1)
        );

        AggregationResults<AuthorDto> results = mongoTemplate.aggregate(aggregation, "author", AuthorDto.class);
        return results.getUniqueMappedResult();
    }

    @Override
    public AuthorDto getAverageAgeOfAuthor() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group().avg("age").as("avgAge")
        );

        AggregationResults<AuthorDto> results = mongoTemplate.aggregate(aggregation, "author", AuthorDto.class);
        return results.getUniqueMappedResult();
    }

    //---------------------------------------MongoClient--------------------------------------------
    @Override
    public List<Author> getAllUseMongoClient() {
        List<Author> authors = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase(databaseEnv);
        MongoCollection<Document> collection = database.getCollection("author");

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document authorsDocument = cursor.next();
                String id = String.valueOf(authorsDocument.getObjectId("_id"));
                String name = authorsDocument.getString("name");
                int age = authorsDocument.getInteger("age");
                List<Book> books = new ArrayList<>();
                List<Document> booksDocument = (List<Document>) authorsDocument.get("books");
                booksDocument.forEach(bookDocument -> {
                    String bookName = bookDocument.getString("name");
                    String publishDate = bookDocument.getString("publishDate");
                    books.add(Book.builder().name(bookName).publishDate(publishDate).build());
                });
                authors.add(Author.builder().id(id).name(name).age(age).books(books).build());
            }
        }

        return authors;
    }

    @Override
    public String createUseMongoClient(Author author) {
        MongoDatabase database = mongoClient.getDatabase(databaseEnv);
        MongoCollection<Document> collection = database.getCollection("author");
        List<Document> booksDocument = new ArrayList<>();

        if (!author.getBooks().isEmpty()) {
            author.getBooks().forEach(book -> {
                Document bookDocument = new Document("name", book.getName())
                        .append("publishDate", book.getPublishDate());
                booksDocument.add(bookDocument);
            });
        }

        Document authorDocument = new Document("name", author.getName())
                .append("age", author.getAge())
                .append("books", booksDocument);

        InsertOneResult insertOneResult = collection.insertOne(authorDocument);

        if (insertOneResult.getInsertedId() != null) {
            return insertOneResult.getInsertedId().toString();
        }

        return "Fail";
    }

    @Override
    public String updateUseMongoClient(Author author) {
        MongoDatabase database = mongoClient.getDatabase(databaseEnv);
        MongoCollection<Document> collection = database.getCollection("author");
        List<Document> booksDocument = new ArrayList<>();

        if (!author.getBooks().isEmpty()) {
            author.getBooks().forEach(book -> {
                Document bookDocument = new Document("name", book.getName())
                        .append("publishDate", book.getPublishDate());
                booksDocument.add(bookDocument);
            });
        }

        Document document = new Document("_id", new ObjectId(author.getId()));
        Document documentToUpdate = new Document("$set", new Document("name", author.getName())
                .append("age", author.getAge())
                .append("books", booksDocument));

        UpdateResult updateResult = collection.updateOne(document, documentToUpdate);

        if (updateResult.getModifiedCount() > 0) {
            return "Success";
        }

        return "Fail";
    }

    @Override
    public String deleteUseMongoClient(String id) {
        MongoDatabase database = mongoClient.getDatabase(databaseEnv);
        MongoCollection<Document> collection = database.getCollection("author");

        DeleteResult deleteResult = collection.deleteOne(new Document("_id", new ObjectId(id)));

        if (deleteResult.getDeletedCount() > 0) {
            return "Success";
        }

        return "Fail";
    }

}
