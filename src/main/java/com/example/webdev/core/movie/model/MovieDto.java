package com.example.webdev.core.movie.model;

import java.util.Objects;

public class MovieDto {

    private String title;

    private String genre;

    private Integer playtime;

    public MovieDto(String title, String genre, Integer playtime) {
        this.title = title;
        this.genre = genre;
        this.playtime = playtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDto movieDto = (MovieDto) o;
        return Objects.equals(title, movieDto.title) && Objects.equals(genre, movieDto.genre)
                && Objects.equals(playtime, movieDto.playtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, playtime);
    }

    @Override
    public String toString() {
        return title + " (" + genre + ", " + playtime + " minutes)";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String genre;
        private Integer playtime;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder withPlaytime(Integer playtime) {
            this.playtime = playtime;
            return this;
        }

        public MovieDto build() {
            return new MovieDto(title, genre, playtime);
        }
    }
}
