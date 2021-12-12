package com.example.webdev.core.movie;


import com.example.webdev.core.movie.model.*;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    void create(MovieDto movieDto);

    void delete(String title);

    void update(MovieDto movieDto);

    Optional<MovieDto> findByTitle(String title);

    List<MovieDto> listMovies();
}
