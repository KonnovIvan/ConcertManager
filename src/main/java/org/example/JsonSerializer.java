package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
public class JsonSerializer implements Serializer {

    private final ObjectMapper objectMapper;

    public JsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String serialize(Artist artist){
        try {

            return objectMapper.writeValueAsString(artist);
        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public Artist deserialize(String data) {
        try {


            return objectMapper.readValue(data, Artist.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveToFile(Object entity, String filePath) {
        try {
            // Перевірка, чи entity є типом Artist
            if (entity instanceof Artist) {
                objectMapper.writeValue(new File(filePath), entity);
            } else {
                throw new IllegalArgumentException("Unsupported type for saving to file: " + entity.getClass());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object loadFromFile(String filePath, Object entity) {
        try {
            // Перевірка, чи entity є типом Artist
            if (entity instanceof Artist) {
                return objectMapper.readValue(new File(filePath), entity.getClass());
            } else {
                throw new IllegalArgumentException("Unsupported type for loading from file: " + entity.getClass());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}