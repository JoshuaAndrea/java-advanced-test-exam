package nl.inholland.exam.movies.rodrigobange.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String writtenBy;
    private LocalDate writtenAt;
    @ManyToOne (cascade = CascadeType.ALL)
    private Movie movie;
    private int score;
    private String content;

    public Review(String author, LocalDate writtenAt, Movie movie, int score, String content) {
        this.writtenBy = author;
        this.writtenAt = writtenAt;
        this.movie = movie;
        this.score = score;
        this.content = content;
    }
}
