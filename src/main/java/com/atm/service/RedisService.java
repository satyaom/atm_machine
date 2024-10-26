package com.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void addAuthToken(String authToken, String key) {
        redisTemplate.opsForValue().set(key, authToken, 1800, TimeUnit.SECONDS);
    }

    public String getAuthToken(String userId) {
        return (String) redisTemplate.opsForValue().get(userId);
    }


    public void expireUserAuthToken(String userId) {
        expireCache("token-" + userId);
    }

    public void expireCache(String id) {
        redisTemplate.expire(id, 0, TimeUnit.SECONDS);
    }

    public void updateExpiryForAuthToken(String userId) {
        redisTemplate.expire("token-" + userId, 1800, TimeUnit.SECONDS);
    }
}
