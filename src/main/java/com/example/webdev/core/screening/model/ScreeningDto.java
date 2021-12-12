package com.example.webdev.core.screening.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ScreeningDto {
    private String movieTitle;
    private String movieGenre;
    private Integer playtime;
    private String roomName;
    private LocalDateTime startTime;

    public ScreeningDto(String movieTitle, String roomName, LocalDateTime startTime) {
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.startTime = startTime;
    }

    public ScreeningDto(String movieTitle, String movieGenre, Integer playtime,
                        String roomName, LocalDateTime startTime) {
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.playtime = playtime;
        this.roomName = roomName;
        this.startTime = startTime;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScreeningDto that = (ScreeningDto) o;
        return Objects.equals(movieTitle, that.movieTitle) && Objects.equals(roomName, that.roomName)
                && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieTitle, roomName, startTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return movieTitle + " (" + movieGenre + ", " + playtime
                + " minutes), screened in room " + roomName
                + ", at " + startTime.format(formatter);
    }
}
