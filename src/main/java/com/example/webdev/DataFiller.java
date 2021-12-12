package com.example.webdev;

import com.example.webdev.core.movie.*;
import com.example.webdev.core.movie.model.*;
import com.example.webdev.core.room.*;
import com.example.webdev.core.room.model.*;
import com.example.webdev.core.screening.*;
import com.example.webdev.core.screening.model.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import java.time.*;

@Component
public class DataFiller {
    private final MovieService movieService;
    private final RoomService roomService;
    private final ScreeningService screeningService;

    public DataFiller(MovieService movieService, RoomService roomService, ScreeningService screeningService) {
        this.movieService = movieService;
        this.roomService = roomService;
        this.screeningService = screeningService;
    }

    @PostConstruct
    private void init() {
        for (int i = 1; i <= 10; i++) {
            MovieDto movieDto = new MovieDto("movie" + i, "action", 10 * i);
            movieService.create(movieDto);
        }

        for (int i = 1; i <= 10; i++) {
            RoomDto roomDto = new RoomDto("room" + i, i + 1, 2 * i);
            roomService.create(roomDto);
        }

//        for (int i = 1; i <= 10; i++) {
//            ScreeningDto screeningDto = new ScreeningDto("movie" + i, "action"
//                    , 10 * i, "room" + i, LocalDateTime.now());
//
//            screeningService.create(screeningDto);
//        }
    }
}
