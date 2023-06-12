package nl.inholland.exam.movies.rodrigobange.repositories;

import nl.inholland.exam.movies.rodrigobange.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
