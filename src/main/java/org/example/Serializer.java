package org.example;

public interface Serializer {

    String serialize(Artist artist);
    Artist deserialize(String data);
}
