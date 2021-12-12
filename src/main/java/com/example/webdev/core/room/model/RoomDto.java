package com.example.webdev.core.room.model;

import java.util.Objects;

public class RoomDto {
    private String roomName;

    private Integer numberOfRows;

    private Integer numberOfCols;

    public RoomDto(String roomName, Integer numberOfRows, Integer numberOfCols) {
        this.roomName = roomName;
        this.numberOfRows = numberOfRows;
        this.numberOfCols = numberOfCols;
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
        RoomDto roomDto = (RoomDto) o;
        return Objects.equals(roomName, roomDto.roomName) && Objects.equals(numberOfRows, roomDto.numberOfRows)
                && Objects.equals(numberOfCols, roomDto.numberOfCols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, numberOfRows, numberOfCols);
    }

    @Override
    public String toString() {
        return "Room " + roomName + " with "
                + numberOfCols * numberOfRows + " seats, "
                + numberOfRows + " rows and "
                + numberOfCols + " columns";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String roomName;

        private Integer numberOfRows;

        private Integer numberOfCols;

        public Builder withRoomName(String roomName) {
            this.roomName = roomName;
            return this;
        }

        public Builder withNumberOfRows(Integer numberOfRows) {
            this.numberOfRows = numberOfRows;
            return this;
        }

        public Builder withNumberOfCols(Integer numberOfCols) {
            this.numberOfCols = numberOfCols;
            return this;
        }

        public RoomDto build() {
            return new RoomDto(roomName, numberOfRows, numberOfCols);
        }
    }
}
