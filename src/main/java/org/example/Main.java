package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.ValidationException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ValidationException, javax.xml.bind.ValidationException {
        ConcertManager concertManager = new ConcertManager();

        Concert concert1 = new Concert("Rock Night", "2023-01-15 20:00", "City Hall");


        Artist artist1 = new Artist.ArtistBuilder()
                .id()
                .concert(concert1)
                .name("The Rockers")
                .genre("Rock")
                .build();

        System.out.println("=======LAB3=========");
        System.out.println(artist1);
        System.out.println("=======LAB3=========");
        // Серіалізація в TXT
        Serializer txtSerializer = new TxtSerializer();
        String txtSerialized = txtSerializer.serialize(artist1);

        // Збереження в TXT файл
        ((TxtSerializer) txtSerializer).saveToFile(artist1, "artist.txt");

        // Зчитування з TXT файлу
        Artist txtDeserialized = ((TxtSerializer) txtSerializer).loadFromFile("artist.txt");


        System.out.println("Original Artist: " + artist1);
        System.out.println("Serialized Data: " + txtSerialized);
        System.out.println("Deserialized Artist: " + txtDeserialized);
        System.out.println("\n =================================");
        System.out.println("JSSSOOOOOOOOOONNNNNNNNN");
        JsonSerializer jsonSerializer = new JsonSerializer(new ObjectMapper());

        String jsonSerialized = jsonSerializer.serialize(artist1);
        System.out.println("Serialized Artist (JSON): " + jsonSerialized);
        jsonSerializer.saveToFile(artist1,"artist.json");

        Artist jsonDeserializedArtist = (Artist) jsonSerializer.loadFromFile("artist.json",artist1);
        System.out.println("Deserialized Artist: " + jsonDeserializedArtist);
        concert1.addArtist(artist1);





        Concert concert2 = new Concert("Jazz Fusion", "2023-02-20 19:30", "Jazz Club");

        Artist artist2 = new Artist.ArtistBuilder()
                .id()
                .concert(concert2)
                .name("Jazz Ensemble")
                .genre("Jazz")
                .build();

        concert2.addArtist(artist2);

        concertManager.addConcert(concert1);
        concertManager.addConcert(concert2);

        concertManager.addArtist(artist1);
        concertManager.addArtist(artist2);

        Client client1 = new Client.ClientBuilder().id().name("John Doe").ticket().build();

        Client client2 = new Client.ClientBuilder().id().name("Alice Smith").ticket().build();

        concertManager.addClient(client1);
        concertManager.addClient(client2);

        concertManager.displayConcerts();
        concertManager.displayArtists();

        concertManager.buyTicket(concert1, client1);
        concertManager.buyTicket(concert2, client2);

        concertManager.displayClients();

        System.out.println();
        System.out.println("===========LAB4==============");

        // Display initial state
        System.out.println("Initial State:");
        concertManager.displayConcerts();
        concertManager.displayArtists();
        concertManager.displayClients();


        // Sort concerts by name, comporable
        concertManager.sortConcertsByName();
        System.out.println("\nAfter Sorting Concerts by Name:");
        concertManager.displayConcerts();

        // Sort artists by name, comporator
        concertManager.sortArtistsByName();
        System.out.println("\nAfter Sorting Artists by Name:");
        concertManager.displayArtists();

        // Sort clients by name, comporator
        concertManager.sortClientsByName();
        System.out.println("\nAfter Sorting Clients by Name:");
        concertManager.displayClients();

        // Filter concerts by genre, stream api
        List<Concert> rockConcerts = concertManager.filterConcertsByGenre("Rock");
        System.out.println("\nRock Concerts:");
        rockConcerts.forEach(concert -> System.out.println(concert.getName()));

        // Get artists by genre, stream api
        List<Artist> jazzArtists = concertManager.getArtistsByGenre("Jazz");
        System.out.println("\nJazz Artists:");
        jazzArtists.forEach(artist -> System.out.println(artist.getName()));

        // Get artists for all concerts, stream api
        List<Artist> allArtists = concertManager.getArtistsForAllConcerts();
        System.out.println("\nArtists for All Concerts:");
        allArtists.forEach(artist -> System.out.println(artist.getName()));


    }
}