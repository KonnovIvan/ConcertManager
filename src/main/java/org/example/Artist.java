package org.example;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Artist {
    private static int artistIdCounter = 1;

    private int artistId;

    @NotNull(message = "Must be not null")
    @Size(min = 3, message = "Must include 3 char")
    private String name;

    @NotNull(message = "Must be not null")
    private String genre;


    private List<Concert> concerts;

    public Artist(String name, String genre) {
        this.artistId = artistIdCounter++;
        this.name = name;
        this.genre = genre;
        this.concerts = new ArrayList<>();
    }

    public int getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public List<Concert> getConcerts() {
        return concerts;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", concerts=" + concerts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) && Objects.equals(genre, artist.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre);
    }

    public Artist() {
    }
    public static class ArtistBuilder {
        private static int artistIdCounter = 1;
        private int artistId;
        private String name;
        private String genre;
        private List<Concert> concerts = new ArrayList<>();

        public Artist.ArtistBuilder id() {
            artistId = artistIdCounter++;
            return this;
        }

        public Artist.ArtistBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Artist.ArtistBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Artist.ArtistBuilder concert(Concert concert) {
            this.concerts.add(concert);
            return this;
        }

        public Artist build() throws ValidationException {
            Artist artist = new Artist(name, genre);

            validateArtist(artist);
            return artist;
        }

        public Artist validateArtist(Artist artist) {
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Artist>> constraintViolations = validator.validate(artist);
            String fieldName = "";
            for (ConstraintViolation constraintViolation : constraintViolations) {
                fieldName += constraintViolation.getPropertyPath().toString().toUpperCase();
            }

            if (fieldName.length() > 0) throw new javax.validation.ValidationException();

            return artist;
        }
    }
}
