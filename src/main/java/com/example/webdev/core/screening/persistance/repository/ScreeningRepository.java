package com.example.webdev.core.screening.persistance.repository;


import com.example.webdev.core.screening.persistance.entity.*;
import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    @Query(value = "SELECT s FROM Screening s WHERE s.movie.title = ?1 and s.room.roomName = ?2 and s.startTime = ?3")
    Optional<Screening> findByTitleAndRoomNameAndTime(String title, String roomName, LocalDateTime localDate);
}
