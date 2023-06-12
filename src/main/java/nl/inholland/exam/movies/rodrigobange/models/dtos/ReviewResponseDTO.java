package nl.inholland.exam.movies.rodrigobange.models.dtos;

import java.time.LocalDate;

public record ReviewResponseDTO(Long id, String writtenBy, LocalDate writtenAt, int score, String content) {
}
