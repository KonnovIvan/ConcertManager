package org.example;

import java.io.*;

//Створити інтерфейс, що буде сериалізувати / десериалізувати
// сутності у різні формати та записувати/зчитувати сутності у/з файлу.
//Імплементувати даний інтерфейс для json, xml, txt формату.
public class TxtSerializer implements Serializer {

    @Override
    public String serialize(Artist artist) {
        return String.format("%s;%s",
                artist.getName(), artist.getGenre());
    }

    @Override
    public Artist deserialize(String data) {
        try {
            String[] parts = data.split(";");
            return new Artist(parts[0], parts[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void saveToFile(Artist artist, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(serialize(artist));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Artist loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String data = reader.readLine();
            return deserialize(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
