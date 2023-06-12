package nl.inholland.exam.movies.rodrigobange.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Review> reviews;
    private int yearOfRelease;
    @Transient
    private double averageScore;

    public Movie(String name, int yearOfRelease) {
        this.name = name;
        this.yearOfRelease = yearOfRelease;
    }
}
