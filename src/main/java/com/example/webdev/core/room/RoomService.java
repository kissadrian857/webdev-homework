package com.example.webdev.core.room;


import com.example.webdev.core.room.model.*;

import java.util.List;

public interface RoomService {
    List<RoomDto> listRooms();

    void update(RoomDto roomDto);

    void create(RoomDto roomDto);

    void delete(String roomName);
}
