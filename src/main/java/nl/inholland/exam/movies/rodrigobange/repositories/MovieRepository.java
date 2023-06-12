package nl.inholland.exam.movies.rodrigobange.repositories;

import nl.inholland.exam.movies.rodrigobange.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByNameAndYearOfRelease(String name, int yearOfRelease);

    Movie findByNameAndYearOfRelease(String name, int yearOfRelease);
}
