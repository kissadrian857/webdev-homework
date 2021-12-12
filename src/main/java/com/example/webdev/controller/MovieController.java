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
    private final SelectedMovieBean selectedMovieBean;

    @ModelAttribute("selectedMovie")
    public MovieDto getSelectedMovie(){
        return selectedMovieBean.getMovieDto();
    }

    @ModelAttribute("movies")
    public List<MovieDto> movies() {
        return movieService.listMovies();
    }

    public MovieController(MovieService movieService, SelectedMovieBean selectedMovieBean) {
        this.movieService = movieService;
        this.selectedMovieBean = selectedMovieBean;
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

    @GetMapping("/initUpdate")
    public String initUpdate(@RequestParam String movieTitle,Model model){
        selectedMovieBean.setMovieDto(movieService.findByTitle(movieTitle).get());
        return "redirect:/update_movie";
    }

    @PostMapping("/updateMovie")
    public String updateMovie(@ModelAttribute(name = "movie") MovieDto movieDto,Model model){
        movieService.update(movieDto);
        selectedMovieBean.setMovieDto(null);
        return "redirect:/movies";
    }

    @GetMapping("/update_movie")
    public String getUpdateMoviePage(){
        return "update_movie";
    }

    @GetMapping("/initNewMovie")
    public String createNewMovie(){
        selectedMovieBean.setMovieDto(new MovieDto());
        return "redirect:/update_movie";
    }

    @PostMapping("/saveNewMovie")
    public String saveNewMovie(@ModelAttribute(name = "movie") MovieDto movieDto,Model model){
        movieService.create(movieDto);
        selectedMovieBean.setMovieDto(null);
        return "redirect:/movies";
    }

}
