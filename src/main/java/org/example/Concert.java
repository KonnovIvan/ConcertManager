package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Concert  implements Comparable<Concert> {
    private static int concertIdCounter = 1;


    private int concertId;
    private String name;
    private String dateTime;
    private List<Artist> artists;
    private String venue;

    public Concert(String name, String dateTime, String venue) {
        this.concertId = concertIdCounter++;
        this.name = name;
        this.dateTime = dateTime;
        this.artists = new ArrayList<>();
        this.venue = venue;
    }

    public int getConcertId() {
        return concertId;
    }

    public String getName() {
        return name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public String getVenue() {
        return venue;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "concertId=" + concertId +
                ", name='" + name + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", artists=" + artists+
                ", venue='" + venue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concert concert = (Concert) o;
        return concertId == concert.concertId && Objects.equals(name, concert.name) && Objects.equals(dateTime, concert.dateTime) && Objects.equals(artists, concert.artists) && Objects.equals(venue, concert.venue);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    public void addArtist(Artist artist) {
        artists.add(artist);
    }

//comparable

    @Override
    public int compareTo(Concert other) {
        return this.name.compareTo(other.name);
    }
}