package org.example;



import java.util.Set;



import java.util.*;
import java.util.stream.Collectors;

class ConcertManager {
    private List<Concert> concerts;
    private List<Artist> artists;
    private List<Client> clients;

    public ConcertManager() {
        this.concerts = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public void addConcert(Concert concert) {
        concerts.add(concert);
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void displayConcerts() {
        System.out.println("Available Concerts:");
        for (Concert concert : concerts) {
            System.out.println("Concert ID: " + concert.getConcertId() +
                    ", Name: " + concert.getName() +
                    ", Date & Time: " + concert.getDateTime() +
                    ", Venue: " + concert.getVenue());
            System.out.println("Artists:");
            for (Artist artist : concert.getArtists()) {
                System.out.println("  - " + artist.getName() + " (" + artist.getGenre() + ")");
            }
            System.out.println();
        }
    }

    public void displayArtists() {
        System.out.println("Available Artists:");
        for (Artist artist : artists) {
            System.out.println("Artist ID: " + artist.getArtistId() +
                    ", Name: " + artist.getName() +
                    ", Genre: " + artist.getGenre());
            System.out.println("Concerts:");
            for (Concert concert : artist.getConcerts()) {
                System.out.println("  - " + concert.getName() +
                        " (" + concert.getDateTime() + ", " + concert.getVenue() + ")");
            }
            System.out.println();
        }
    }

    public void displayClients() {
        System.out.println("Registered Clients:");
        for (Client client : clients) {
            System.out.println("Client ID: " + client.getClientId() +
                    ", Name: " + client.getName());
            System.out.println("Tickets:");
            for (Ticket ticket : client.getTickets()) {
                System.out.println("  - " + ticket.getConcert().getName() +
                        " (" + ticket.getConcert().getDateTime() + ")");
            }
            System.out.println();
        }
    }

    public Concert getConcertById(int concertId) {
        for (Concert concert : concerts) {
            if (concert.getConcertId() == concertId) {
                return concert;
            }
        }
        return null;
    }

    public Artist getArtistById(int artistId) {
        for (Artist artist : artists) {
            if (artist.getArtistId() == artistId) {
                return artist;
            }
        }
        return null;
    }

    public Client getClientById(int clientId) {
        for (Client client : clients) {
            if (client.getClientId() == clientId) {
                return client;
            }
        }
        return null;
    }

    public void buyTicket(int concertId, int clientId) {
        Concert concert = getConcertById(concertId);
        Client client = getClientById(clientId);

        if (concert != null && client != null) {
            Ticket ticket = new Ticket(concert, client);
            client.buyTicket(ticket);
            System.out.println("Ticket purchased successfully for concert: " + concert.getName());
        } else {
            System.out.println("Invalid concert or client ID. Please try again.");
        }
    }

    @Override
    public String toString() {
        return "ConcertManager{" +
                "concerts=" + concerts +
                ", artists=" + artists +
                ", clients=" + clients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcertManager that = (ConcertManager) o;
        return Objects.equals(concerts, that.concerts) && Objects.equals(artists, that.artists) && Objects.equals(clients, that.clients);
    }


//Для заданої структури класів зробити сервісні методи (не менше трьох) для роботи
// з колекціями відповідних об'єктів.
//Обов'язково використати інтерфейси Comparable та Comparator.
//Аналогічні методи написани з використанням Stream API.
    public void sortConcertsByName() {
        Collections.sort(concerts);
    }


    public void sortArtistsByName() {
        Comparator<Artist> artistComparator = Comparator.comparing(Artist::getName);
        artists.sort(artistComparator);
    }

    public void sortClientsByName() {
        Comparator<Client> clientComparator = Comparator.comparing(Client::getName);
        clients.sort(clientComparator);
    }

    //(Stream API)
    public List<Concert> filterConcertsByGenre(String genre) {
        return concerts.stream()
                .filter(concert -> concert.getArtists().stream()
                        .anyMatch(artist -> artist.getGenre().equalsIgnoreCase(genre)))
                .collect(Collectors.toList());
    }

    // (Stream API)
    public List<Artist> getArtistsByGenre(String genre) {
        return artists.stream()
                .filter(artist -> artist.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    // (Stream API)
    public List<Artist> getArtistsForAllConcerts() {
        Set<Artist> artistsSet = concerts.stream()
                .flatMap(concert -> concert.getArtists().stream())
                .collect(Collectors.toSet());

        return new ArrayList<>(artistsSet);
    }


}
