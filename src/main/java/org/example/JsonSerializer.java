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

    public void saveToFile(Artist entity, String filePath) {
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

    public Artist loadFromFile(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), Artist.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}