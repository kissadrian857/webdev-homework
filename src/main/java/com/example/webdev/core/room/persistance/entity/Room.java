package com.example.webdev.core.room.persistance.entity;

import com.example.webdev.core.screening.persistance.entity.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String roomName;

    private Integer numberOfRows;

    private Integer numberOfCols;

    @OneToMany(mappedBy = "room")
    private Set<Screening> screenings;

    public Room() {
    }

    public Room(String roomName, Integer numberOfRows, Integer numberOfCols) {
        this.roomName = roomName;
        this.numberOfRows = numberOfRows;
        this.numberOfCols = numberOfCols;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public Integer getNumberOfCols() {
        return numberOfCols;
    }

    public void setNumberOfCols(Integer numberOfCols) {
        this.numberOfCols = numberOfCols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(id, room.id) && Objects.equals(roomName, room.roomName)
                && Objects.equals(numberOfRows, room.numberOfRows) && Objects.equals(numberOfCols, room.numberOfCols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomName, numberOfRows, numberOfCols);
    }

    public Set<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(Set<Screening> screenings) {
        this.screenings = screenings;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id
                + ", roomName='" + roomName + '\''
                + ", numberOfRows=" + numberOfRows
                + ", numberOfCols=" + numberOfCols
                + '}';
    }
}
