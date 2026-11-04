package pt.rumos.mymovies.controller;

import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.rumos.mymovies.dto.MovieRequest;
import pt.rumos.mymovies.dto.MovieResponse;
import pt.rumos.mymovies.dto.RatingUpdateRequest;
import pt.rumos.mymovies.model.Movie;
import pt.rumos.mymovies.service.MovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //Lista fixa em memória com 22 filmes para testes e validação
//    private final List<Movie> movies = new ArrayList<>(List.of(
//            new Movie(1L, "Interstellar", "Christopher Nolan", 2014),
//            new Movie(2L, "The Matrix", "Lana & Lilly Wachowski", 1999),
//            new Movie(3L, "Inception", "Christopher Nolan", 2010),
//            new Movie(4L, "The Dark Knight", "Christopher Nolan", 2008),
//            new Movie(5L, "Pulp Fiction", "Quentin Tarantino", 1994),
//            new Movie(6L, "The Godfather", "Francis Ford Coppola", 1972),
//            new Movie(7L, "Fight Club", "David Fincher", 1999),
//            new Movie(8L, "Forrest Gump", "Robert Zemeckis", 1994),
//            new Movie(9L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", 2001),
//            new Movie(10L, "Star Wars: Episode V - The Empire Strikes Back", "Irvin Kershner", 1980),
//            new Movie(11L, "The Matrix Reloaded", "Lana & Lilly Wachowski", 2003),
//            new Movie(12L, "The Matrix Revolutions", "Lana & Lilly Wachowski", 2003),
//            new Movie(13L, "Gladiator", "Ridley Scott", 2000),
//            new Movie(14L, "Back to the Future", "Robert Zemeckis", 1985),
//            new Movie(15L, "Jurassic Park", "Steven Spielberg", 1993),
//            new Movie(16L, "Alien", "Ridley Scott", 1979),
//            new Movie(17L, "Blade Runner 2049", "Denis Villeneuve", 2017),
//            new Movie(18L, "Dune", "Denis Villeneuve", 2021),
//            new Movie(19L, "Dune: Part Two", "Denis Villeneuve", 2024),
//            new Movie(20L, "Oppenheimer", "Christopher Nolan", 2023),
//            new Movie(21L, "Avatar", "James Cameron", 2009)
//            ));


//    @GetMapping
//    public List<Movie> listAll() {
//        return movies;
//    }

//    @GetMapping
//    public List<MovieResponse> findAll() {
//
//        return movieService.findAll();
//
//    }

//    @GetMapping("/{id}")
//    public Movie getById(@PathVariable Long id) {
//        return movies.stream()
//                .filter(movie -> movie.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {

        return movieService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/search")
//    public List<Movie> searchByTitle(
//            @RequestParam(required = false) String title) {
//
//        if (title == null || title.isBlank()) {
//            return movies;
//        }
//
//        return movies.stream()
//                .filter(movie -> movie.getTitle()
//                        .toLowerCase()
//                        .contains(title.toLowerCase()))
//                .toList();
//    }


// TODO: Implementar POST /api/movies -> Com @Valid @RequestBody e 201 Created
//@PostMapping
//public ResponseEntity<MovieResponse> create(
//        @Valid @RequestBody MovieRequest request) {
//
//    Long nextId = movies.stream()
//            .mapToLong(Movie::getId)
//            .max()
//            .orElse(0L) + 1;
//
//    Movie movie = new Movie(
//            nextId,
//            request.title(),
//            request.director(),
//            request.year()
//    );
//
//    movies.add(movie);
//
//    return ResponseEntity
//            .status(HttpStatus.CREATED)
//            .body(MovieResponse.fromDomain(movie));
//}


    @PostMapping
    public ResponseEntity<MovieResponse> create(
            @Valid @RequestBody MovieRequest request) {

        MovieResponse response = movieService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // TODO: Implementar PUT /api/movies/{id} -> Com @Valid, 200 OK ou 404 Not Found
//    @PutMapping("/{id}")
//    public ResponseEntity<MovieResponse> update(
//            @PathVariable Long id,
//            @Valid @RequestBody MovieRequest request) {
//
//        Optional<Movie> optionalMovie = movies.stream()
//                .filter(movie -> movie.getId().equals(id))
//                .findFirst();
//
//        if (optionalMovie.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Movie movie = optionalMovie.get();
//
//        movie.setTitle(request.title());
//        movie.setDirector(request.director());
//        movie.setYear(request.year());
//
//        return ResponseEntity.ok(
//                MovieResponse.fromDomain(movie)
//        );
//    }

// TODO: Implementar DELETE /api/movies/{id} -> Retornar 204 No Content ou 404 Not

    //    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//
//        boolean removed = movies.removeIf(
//                movie -> movie.getId().equals(id)
//        );
//
//        if (!removed) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean deleted = movieService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    //    @PatchMapping("/{id}/rating")
//    public ResponseEntity<MovieResponse> updateRating(@PathVariable Long id, @Valid @RequestBody RatingUpdateRequest request) {
//
//        var optionalMovie = movies.stream()
//                .filter(movie -> movie.getId().equals(id))
//                .findFirst();
//
//        if (optionalMovie.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Movie movie = optionalMovie.get();
//
//        movie.setRating(request.rating());
//
//        return ResponseEntity.ok(
//                MovieResponse.fromDomain(movie)
//        );
//     }
    @PatchMapping("/{id}/rating")
    public ResponseEntity<MovieResponse> updateRating(
            @PathVariable Long id,
            @Valid @RequestBody RatingUpdateRequest request) {

        return movieService.updateRating(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<MovieResponse> findAll(
            @RequestParam(required = false) String title) {

        return movieService.findAll(title);
    }
}

