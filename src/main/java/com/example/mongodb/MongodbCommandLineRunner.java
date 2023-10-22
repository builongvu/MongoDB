package com.example.mongodb;

import com.example.mongodb.repository.AuthorRepository;
import com.example.mongodb.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongodbCommandLineRunner implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    @Override
    public void run(String... args) throws Exception {
        //----------------------Spring Data MongoDB (MongoRepository)--------------------------------
//        List.of(
//                Author.builder().name("Van A").age(30).books(List.of(
//                        Book.builder().name("toan").publishDate("12-02-1997").build(),
//                        Book.builder().name("van").publishDate("09-12-2004").build())
//                ).build(),
//                Author.builder().name("Thi B").age(60).books(List.of(
//                        Book.builder().name("anh").publishDate("12-03-2012").build()
//                )).build()
//        ).forEach(authorService::createUseMongoRepository);

//        authorRepository.save(Author.builder().name("Van A").age(30).books(List.of(Book.builder().name("Van").publishDate("12-12-1980").build())).build());

//        Author author = Author.builder()
//                .id("653343f63e971f62cbe14df4")
//                .name("Nguyen Van A")
//                .age(56)
//                .books(List.of(
//                        Book.builder().name("toan lop 3").publishDate("12-12-1970").build(),
//                        Book.builder().name("GDCD").publishDate("12-05-2000").build()
//                ))
//                .build();
//        System.out.println(authorService.updateUseMongoRepository(author));

//        authorRepository.findByNameStartsWith("V").forEach(System.out::println);

//        authorRepository.findByNameAndAgeUseOr().forEach(System.out::println);

//        authorRepository.findByBookName("Van").forEach(System.out::println);


        //----------------------------------MongoTemplate-------------------------------------------
//        Author author = Author.builder().name("Thi C").age(20).build();
//        System.out.println(authorService.createUseMongoTemplate(author));

//        authorService.getAllUseMongoTemplate().forEach(System.out::println);

//        Author author = authorRepository.findById("65339d50d41c76372a114457").get();
//        author.setAge(25);
//        System.out.println(authorService.updateUseMongoTemplate(author));

//        authorService.deleteUseMongoTemplate("6533483c1a70327fe4cf1a1c");

//        authorService.customQuery().forEach(System.out::println);

//        authorService.customQuery2().forEach(System.out::println);

//        authorService.search("a", 30, "a").forEach(System.out::println);

        // Aggregation
//        System.out.println(authorService.getAuthorWithMostBooks());

//        System.out.println(authorService.getAverageAgeOfAuthor());

        //------------------------------------MongoClient-------------------------------------------
//        authorService.getAllUseMongoClient().forEach(System.out::println);

//        Author author = Author.builder()
//                .name("Van C")
//                .age(54)
//                .books(List.of(
//                        Book.builder().name("vat ly").publishDate("12-12-1970").build()
//                ))
//                .build();
//        System.out.println(authorService.createUseMongoClient(author));

//        Author author = Author.builder()
//                .id("6534e6ddfbe0bd428a1abf47")
//                .name("Van C")
//                .age(80)
//                .books(List.of(
//                        Book.builder().name("lich su").publishDate("12-12-1970").build()
//                ))
//                .build();
//        System.out.println(authorService.updateUseMongoClient(author));

//        System.out.println(authorService.deleteUseMongoClient("6534e6ddfbe0bd428a1abf47"));

    }

}
