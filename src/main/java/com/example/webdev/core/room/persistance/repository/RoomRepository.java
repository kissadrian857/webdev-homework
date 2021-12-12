package com.example.webdev.core.room.persistance.repository;



import com.example.webdev.core.room.persistance.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomName(String roomName);
}
