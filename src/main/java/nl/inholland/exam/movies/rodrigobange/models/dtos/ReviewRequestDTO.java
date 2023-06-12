package nl.inholland.exam.movies.rodrigobange.models.dtos;

public record ReviewRequestDTO(Long movieId, String author, String content, int score) {
}
