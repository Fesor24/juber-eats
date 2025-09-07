package com.app.JuberEats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CacheService implements ICacheService{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <T> void save(String key, T object) {
        redisTemplate.opsForValue().set(key, object);
    }

    @Override
    public <T> Optional<T> getItem(String key, Class<T> clazz) {
        Object value =  redisTemplate.opsForValue().get(key);

        return Optional.ofNullable(clazz.cast(value));
    }

    @Override
    public void deleteItem(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }
}
