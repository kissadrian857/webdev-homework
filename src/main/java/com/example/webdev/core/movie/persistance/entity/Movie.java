package com.example.webdev.core.movie.persistance.entity;



import com.example.webdev.core.screening.persistance.entity.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String title;

    private String genre;

    private Integer playtime;

    @OneToMany(mappedBy = "movie")
    private Set<Screening> screenings;

    public Movie() {
    }

    public Movie(String title, String genre, Integer playtime) {
        this.title = title;
        this.genre = genre;
        this.playtime = playtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title)
                && Objects.equals(genre, movie.genre) && Objects.equals(playtime, movie.playtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, playtime);
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    public Set<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(Set<Screening> screenings) {
        this.screenings = screenings;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", genre='" + genre + '\''
                + ", playtime=" + playtime
                + '}';
    }
}
