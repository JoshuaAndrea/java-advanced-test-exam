package nl.inholland.exam.movies.rodrigobange.models.dtos;

import nl.inholland.exam.movies.rodrigobange.models.Review;

import java.util.List;

public record MovieResponseDTO(long id, String name, List<Review> reviews, int yearOfRelease) {
}
