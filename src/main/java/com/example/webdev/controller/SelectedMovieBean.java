package com.example.webdev.controller;

import com.example.webdev.core.movie.model.*;
import org.springframework.stereotype.*;

@Component
public class SelectedMovieBean {
    private MovieDto movieDto;

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }
}
