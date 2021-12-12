package com.example.webdev.core.movie.persistance.repository;



import com.example.webdev.core.movie.persistance.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
}
