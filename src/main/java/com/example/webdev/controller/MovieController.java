package com.example.webdev.controller;

import com.example.webdev.core.movie.*;
import com.example.webdev.core.movie.model.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MovieController {
    private final MovieService movieService;

    @ModelAttribute("movies")
    public List<MovieDto> movies() {
        return movieService.listMovies();
    }

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String getMoviesPage() {
        return "movies";
    }

    @GetMapping("/deleteMovie")
    public String deleteMovie(@RequestParam String movieTitle, Model model) {
        movieService.delete(movieTitle);
        return "redirect:/movies";
    }
}
