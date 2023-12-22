package org.example;

public interface Serializer {

    String serialize(Artist artist);
    Artist deserialize(String data);
    Artist loadFromFile(String filePath);
    void saveToFile(Artist artist, String filePath);
}
