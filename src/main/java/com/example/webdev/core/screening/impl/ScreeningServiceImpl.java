package com.example.webdev.core.screening.impl;



import com.example.webdev.core.movie.persistance.entity.*;
import com.example.webdev.core.movie.persistance.repository.*;
import com.example.webdev.core.room.persistance.entity.*;
import com.example.webdev.core.room.persistance.repository.*;
import com.example.webdev.core.screening.*;
import com.example.webdev.core.screening.model.*;
import com.example.webdev.core.screening.persistance.entity.*;
import com.example.webdev.core.screening.persistance.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService {
    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;

    public ScreeningServiceImpl(RoomRepository roomRepository,
                                MovieRepository movieRepository,
                                ScreeningRepository screeningRepository) {
        this.roomRepository = roomRepository;
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
    }

    @Override
    public void create(ScreeningDto screeningDto) {
        Optional<Movie> optionalMovie = movieRepository.findByTitle(screeningDto.getMovieTitle());
        Optional<Room> optionalRoom = roomRepository.findByRoomName(screeningDto.getRoomName());
        if (overlapBreak(screeningDto.getStartTime(), screeningDto.getRoomName())) {
            throw new IllegalArgumentException("break overlap");
        } else if (overlapAnyMovie(screeningDto.getStartTime(), screeningDto.getRoomName())) {
            throw new IllegalArgumentException("movie overlap");
        } else if (optionalMovie.isPresent() && optionalRoom.isPresent()) {
            Screening screening = new Screening(optionalMovie.get(), optionalRoom.get(),
                    screeningDto.getStartTime());
            screeningRepository.save(screening);
        } else {
            throw new IllegalArgumentException("Az egyik megadott paraméter nem megfelelő!!");
        }
    }

    @Override
    public void delete(ScreeningDto screeningDto) {
        Optional<Screening> optionalScreening =
                screeningRepository.findByTitleAndRoomNameAndTime(screeningDto.getMovieTitle(),
                screeningDto.getRoomName(), screeningDto.getStartTime());
        if (optionalScreening.isPresent()) {
            screeningRepository.delete(optionalScreening.get());
        } else {
            throw new IllegalArgumentException("Az egyik megadott paraméter nem megfelelő!!");
        }
    }

    @Override
    public List<ScreeningDto> listScreenings() {
        return screeningRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ScreeningDto convertEntityToDto(Screening screening) {
        return new ScreeningDto(screening.getMovie().getTitle(),
                screening.getMovie().getGenre(),
                screening.getMovie().getPlaytime(),
                screening.getRoom().getRoomName(),
                screening.getStartTime());
    }

    private Optional<ScreeningDto> convertEntityToDto(Optional<Screening> optionalScreening) {
        return optionalScreening.isEmpty() ? Optional.empty() :
                Optional.of(convertEntityToDto(optionalScreening.get()));
    }

    private List<Container> getAllStartTimes(String roomName) {
        return screeningRepository.findAll().stream().filter(screening -> screening.getRoom()
                        .getRoomName().equals(roomName))
                .map(screening -> new Container(screening.getStartTime(),
                        screening.getMovie().getPlaytime()))
                .collect(Collectors.toList());
    }

    private boolean overlapAnyMovie(LocalDateTime desiredStart, String roomName) {
        return getAllStartTimes(roomName).stream()
                .map(container -> {
                    LocalDateTime start = container.startTime;
                    LocalDateTime end = container.startTime.plusMinutes(container.duration);
                    if (start.equals(desiredStart) || end.equals(desiredStart)
                            || (desiredStart.isAfter(start) && desiredStart.isBefore(end))) {
                        return true;
                    }
                    return false;
                })
                .reduce(false, (val1, val2) -> val1 || val2);
    }

    private boolean overlapBreak(LocalDateTime desiredStart, String roomName) {
        return getAllStartTimes(roomName).stream()
                .map(container -> {
                    LocalDateTime endOfMovie = container.startTime.plusMinutes(container.duration);
                    LocalDateTime endOfBreak = endOfMovie.plusMinutes(10);
                    if (desiredStart.isAfter(endOfMovie) && desiredStart.isBefore(endOfBreak)) {
                        return true;
                    }
                    return false;
                })
                .reduce(false, (val1, val2) -> val1 || val2);
    }

    private class Container {
        private LocalDateTime startTime;
        private Integer duration;

        public Container(LocalDateTime startTime, Integer duration) {
            this.startTime = startTime;
            this.duration = duration;
        }
    }
}
