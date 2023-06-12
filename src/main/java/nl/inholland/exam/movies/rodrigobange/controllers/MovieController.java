package nl.inholland.exam.movies.rodrigobange.controllers;

import nl.inholland.exam.movies.rodrigobange.models.Movie;
import nl.inholland.exam.movies.rodrigobange.models.dtos.MovieRequestDTO;
import nl.inholland.exam.movies.rodrigobange.models.dtos.MovieResponseDTO;
import nl.inholland.exam.movies.rodrigobange.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
@CrossOrigin("*")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Object> getMovies() {
        List<Movie> movies = movieService.getAllMovies();
        List<MovieResponseDTO> responses = movies.stream()
                .map(this::convertToMovieResponseDTO)
                .toList();

        return ResponseEntity.status(200).body(responses);
    }

    @PostMapping
    public ResponseEntity<Object> addMovie(@RequestBody MovieRequestDTO movie) {
        Movie savedMovie = movieService.addMovie(movie);
        MovieResponseDTO response = convertToMovieResponseDTO(savedMovie);
        return ResponseEntity.status(201).body(response);
    }

    private MovieResponseDTO convertToMovieResponseDTO(Movie movie) {
        return new MovieResponseDTO(
                movie.getId(),
                movie.getName(),
                movie.getReviews(),
                movie.getYearOfRelease()
        );
    }
}
