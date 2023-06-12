package nl.inholland.exam.movies.rodrigobange.configurators;

import jakarta.transaction.Transactional;
import nl.inholland.exam.movies.rodrigobange.models.Movie;
import nl.inholland.exam.movies.rodrigobange.services.MovieService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class ApplicationInitializer implements ApplicationRunner {

    private final MovieService movieService;

    public ApplicationInitializer(MovieService movieService) {
        this.movieService = movieService;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        createMovies();
    }

    private void createMovies() throws IOException {
        movieService.saveAllMovies(
                Files.readAllLines(Paths.get("movies.csv"))
                        .stream()
                        .map(line -> line.split(","))
                        .map(data -> new Movie(data[0], Integer.parseInt(data[1])))
                        .collect(Collectors.toList())
        );
        movieService.findAllMovies().forEach(System.out::println);
    }
}
