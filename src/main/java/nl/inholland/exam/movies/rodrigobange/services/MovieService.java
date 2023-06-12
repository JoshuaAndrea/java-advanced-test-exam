package nl.inholland.exam.movies.rodrigobange.services;

import nl.inholland.exam.movies.rodrigobange.models.Movie;
import nl.inholland.exam.movies.rodrigobange.models.Review;
import nl.inholland.exam.movies.rodrigobange.models.dtos.MovieRequestDTO;
import nl.inholland.exam.movies.rodrigobange.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        for (Movie movie: movies ) {
            Stream<Review> streamReview = movie.getReviews().stream();
            movie.setAverageScore(streamReview.mapToInt(Review::getScore).average().orElse(0));
        }

        return movies;
    }

    public Movie addMovie(MovieRequestDTO request) {

        boolean movieExists = movieRepository.existsByNameAndYearOfRelease(request.name(), request.yearOfRelease());

        if (!movieExists) { // If movie doesn't exist
            Movie convertedMovie = convertToMovie(request);
            return movieRepository.save(convertedMovie);
        }
        return movieRepository.findByNameAndYearOfRelease(request.name(), request.yearOfRelease());
    }

    public List<Review> getReviewsByMovieId(Long id) {
        return getMovieById(id).getReviews();
    }

    private Movie convertToMovie(MovieRequestDTO request) {
        return new Movie(
                request.name(),
                request.yearOfRelease()
        );
    }

    public void saveAllMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

    public Iterable<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public void addReviewToMovie(Movie movie, Review review) {
        movie.getReviews().add(review);
        movieRepository.save(movie);
    }
}
