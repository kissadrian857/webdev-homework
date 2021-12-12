package com.example.webdev.controller;

import com.example.webdev.core.room.*;
import com.example.webdev.core.room.model.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @ModelAttribute("rooms")
    public List<RoomDto> rooms() {
        return roomService.listRooms();
    }

    @GetMapping("/rooms")
    public String getRoomsPage() {
        return "rooms";
    }
}
