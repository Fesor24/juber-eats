package com.app.JuberEats.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class JsonConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String serialize(Object obj){
        try{
            return mapper.writeValueAsString(obj);
        }catch(JsonProcessingException ex){
            throw new RuntimeException("Serialization error: " + ex.getMessage());
        }
    }

    public static <T> Optional<T> deserialize(String str, Class<T> clazz){
        try{

            if(str.isEmpty()){
                return Optional.empty();
            }


            var value = mapper.readValue(str, clazz);

            return Optional.of(value);
        } catch(JsonProcessingException ex){
            throw new RuntimeException("Deserialization error:" + ex.getMessage());
        }
    }
}
