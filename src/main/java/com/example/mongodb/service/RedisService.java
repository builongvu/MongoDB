package com.example.mongodb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

//    public void put(String key, Object value, int expiredTime, TimeUnit timeUnit) {
//        key = formatKeyAppPrefix(key);
//        redisTemplate.opsForValue().set(key, value);
//        if (expiredTime != -1) {
//            redisTemplate.expire(key, expiredTime, timeUnit);
//        }
//    }

//    public boolean isExpired(String key) {
//        key = formatKeyAppPrefix(key);
//        return !redisTemplate.hasKey(key);
//    }

//    public Long getExpire(String key) {
//        key = formatKeyAppPrefix(key);
//        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
//    }

    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getString(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setList(String key, List<Object> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    public List<Object> getAllList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public Object getListByIndex(String key, int index) {
        return redisTemplate.opsForList().index(key, index);
    }

    public void setHash(String key, Map<String, Object> values) {
        redisTemplate.opsForHash().putAll(key, values);
    }

    public Map<Object, Object> getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Object getAValueFromHash(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

}
