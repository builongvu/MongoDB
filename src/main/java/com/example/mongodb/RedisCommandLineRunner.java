package com.example.mongodb;

import com.example.mongodb.document.Author;
import com.example.mongodb.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RedisCommandLineRunner implements CommandLineRunner {

    private final RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        //-----------------------------------------String-------------------------------------------
//        redisService.setString("myName", "Vu");
//
//        System.out.println(redisService.getString("myName"));

//        redisService.setObject("student", Student.builder().id(1).name("hello").build());
//
//        System.out.println(redisService.getObject("student"));

        //----------------------------------------List----------------------------------------------
//        redisService.setList("letter", List.of("a", "b", "c"));
//
//        redisService.getAllList("letter").forEach(System.out::println);
//
//        System.out.println(redisService.getListByIndex("letter", 1));

        //----------------------------------------Hash----------------------------------------------
//        redisService.setHash("animal", Map.of("type", "cat", "color", "yellow"));

//        redisService.getHash("animal").entrySet().forEach(entry -> {
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        });

//        System.out.println(redisService.getAValueFromHash("animal", "type"));
    }
}
