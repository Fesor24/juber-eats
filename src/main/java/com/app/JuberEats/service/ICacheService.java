package com.app.JuberEats.service;

import java.util.Optional;

public interface ICacheService {
    <T> void save(String key, T object);
    <T> void save(String key, T object, Long ttlInMinutes);
    <T> Optional<T> getItem(String key, Class<T> clazz);
    void deleteItem(String key);
}
