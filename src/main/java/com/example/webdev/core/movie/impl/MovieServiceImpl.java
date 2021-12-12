package com.example.webdev.core.movie.impl;


import com.example.webdev.core.movie.*;
import com.example.webdev.core.movie.model.*;
import com.example.webdev.core.movie.persistance.entity.*;
import com.example.webdev.core.movie.persistance.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void create(MovieDto movieDto) {
        validation(movieDto);
        Movie movie = new Movie(movieDto.getTitle(), movieDto.getGenre(), movieDto.getPlaytime());
        movieRepository.save(movie);
    }

    @Override
    public void delete(String title) {
        Optional<Movie> optionalMovie = movieRepository.findByTitle(title);
        if (optionalMovie.isPresent()) {
            movieRepository.delete(optionalMovie.get());
        } else {
            throw new IllegalArgumentException("Couldn't find movie with the title:" + title);
        }
    }

    @Override
    public void update(MovieDto movieDto) {
        validation(movieDto);
        Optional<Movie> optionalMovie = movieRepository.findByTitle(movieDto.getTitle());
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setGenre(movieDto.getGenre());
            movie.setPlaytime(movieDto.getPlaytime());
            movieRepository.save(movie);
        } else {
            throw new IllegalArgumentException("Couldn't find movie with the title:" + movieDto.getTitle());
        }
    }

    @Override
    public List<MovieDto> listMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> findByTitle(String title) {
        return convertEntityToDto(movieRepository.findByTitle(title));
    }

    private MovieDto convertEntityToDto(Movie movie) {
        return MovieDto.builder().withTitle(movie.getTitle())
                .withGenre(movie.getGenre())
                .withPlaytime(movie.getPlaytime())
                .build();
    }

    private Optional<MovieDto> convertEntityToDto(Optional<Movie> movie) {
        return movie.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(movie.get()));
    }

    private void validation(MovieDto movieDto) {
        Objects.requireNonNull(movieDto, "Movie cannot be null");
        Objects.requireNonNull(movieDto.getTitle(), "Movie title cannot be null");
        Objects.requireNonNull(movieDto.getGenre(), "Movie genre cannot be null");
        Objects.requireNonNull(movieDto.getPlaytime(), "Movie playtime cannot be null");
    }
}
