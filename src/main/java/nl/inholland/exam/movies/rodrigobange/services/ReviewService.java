package nl.inholland.exam.movies.rodrigobange.services;

import nl.inholland.exam.movies.rodrigobange.models.Movie;
import nl.inholland.exam.movies.rodrigobange.models.Review;
import nl.inholland.exam.movies.rodrigobange.models.dtos.ReviewRequestDTO;
import nl.inholland.exam.movies.rodrigobange.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieService movieService;

    public ReviewService(ReviewRepository reviewRepository, MovieService movieService) {
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
    }

    public Review addReview(ReviewRequestDTO request) {
        // Get movie
        Movie movie = movieService.getMovieById(request.movieId());

        Review review = new Review(
                request.author(),
                LocalDate.now(),
                movie,
                request.score(),
                request.content()
        );

        movieService.addReviewToMovie(movie, review);

        // Save review
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
