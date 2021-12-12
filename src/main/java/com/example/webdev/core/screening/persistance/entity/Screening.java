package com.example.webdev.core.screening.persistance.entity;


import com.example.webdev.core.movie.persistance.entity.*;
import com.example.webdev.core.room.persistance.entity.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    public Screening() {
    }

    public Screening(Movie movie, Room room, LocalDateTime startTime) {
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Screening screening = (Screening) o;
        return Objects.equals(id, screening.id) && Objects.equals(movie, screening.movie)
                && Objects.equals(room, screening.room) && Objects.equals(startTime, screening.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, room, startTime);
    }

    @Override
    public String toString() {
        return "Screening{"
                + "id=" + id
                + ", movie=" + movie
                + ", room=" + room
                + ", startTime=" + startTime
                + '}';
    }
}
