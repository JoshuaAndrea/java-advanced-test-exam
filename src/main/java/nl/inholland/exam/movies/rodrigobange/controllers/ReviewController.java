package nl.inholland.exam.movies.rodrigobange.controllers;

import nl.inholland.exam.movies.rodrigobange.models.Review;
import nl.inholland.exam.movies.rodrigobange.models.dtos.ReviewRequestDTO;
import nl.inholland.exam.movies.rodrigobange.models.dtos.ReviewResponseDTO;
import nl.inholland.exam.movies.rodrigobange.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
@CrossOrigin("*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Object> addReview(@RequestBody ReviewRequestDTO request) {
        Review review = reviewService.addReview(request);
        ReviewResponseDTO response = convertToReviewResponseDTO(review);
        return ResponseEntity.status(201).body(response);
    }

    private ReviewResponseDTO convertToReviewResponseDTO(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getWrittenBy(),
                review.getWrittenAt(),
                review.getScore(),
                review.getContent()
        );
    }
}
