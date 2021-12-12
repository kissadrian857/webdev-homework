package com.example.webdev.core.room.impl;


import com.example.webdev.core.room.*;
import com.example.webdev.core.room.model.*;
import com.example.webdev.core.room.persistance.entity.*;
import com.example.webdev.core.room.persistance.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDto> listRooms() {
        return roomRepository.findAll().stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(RoomDto roomDto) {
        validate(roomDto);
        Optional<Room> optionalRoom = roomRepository.findByRoomName(roomDto.getRoomName());
        if (optionalRoom.isPresent()) {
            Room r = optionalRoom.get();
            r.setNumberOfCols(roomDto.getNumberOfCols());
            r.setNumberOfRows(roomDto.getNumberOfRows());
            roomRepository.save(r);
        } else {
            throw new IllegalArgumentException("Nincs " + roomDto.getRoomName()
                    + " nevű szoba");
        }
    }

    @Override
    public void create(RoomDto roomDto) {
        validate(roomDto);
        Room room = new Room(roomDto.getRoomName(), roomDto.getNumberOfRows(),
                roomDto.getNumberOfCols());
        roomRepository.save(room);
    }

    @Override
    public void delete(String roomName) {
        Objects.requireNonNull(roomName, "Room name cannot be null");
        Optional<Room> optionalRoom = roomRepository.findByRoomName(roomName);
        if (optionalRoom.isPresent()) {
            roomRepository.delete(optionalRoom.get());
        } else {
            throw new IllegalArgumentException("Nincs " + roomName + " nevű szoba");
        }
    }

    private RoomDto convertEntityToDto(Room room) {
        return RoomDto.builder().withRoomName(room.getRoomName())
                .withNumberOfRows(room.getNumberOfRows())
                .withNumberOfCols(room.getNumberOfCols())
                .build();
    }

    private Optional<RoomDto> convertEntityToDto(Optional<Room> optionalRoom) {
        return optionalRoom.isEmpty()
                ? Optional.empty() : Optional.of(convertEntityToDto(optionalRoom.get()));
    }

    private void validate(RoomDto roomDto) {
        Objects.requireNonNull(roomDto, "Room cannot be null");
        Objects.requireNonNull(roomDto.getRoomName(), "Room name cannot be null");
        Objects.requireNonNull(roomDto.getNumberOfRows(), "The number of rows cannot be null");
        Objects.requireNonNull(roomDto.getNumberOfCols(), "The number of cols cannot be null");
    }
}
