package com.app.JuberEats.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String serialize(Object obj){
        try{
            return mapper.writeValueAsString(obj);
        }catch(JsonProcessingException ex){
            throw new RuntimeException("Serialization error: " + ex.getMessage());
        }
    }

    public static <T> T deserialize(String str, Class<T> clazz){
        try{
            return mapper.readValue(str, clazz);
        } catch(JsonProcessingException ex){
            throw new RuntimeException("Deserialization error:" + ex.getMessage());
        }
    }
}
