package pt.rumos.mymovies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.rumos.mymovies.model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
        List<Movie> findByTitleContainingIgnoreCase(String title);
        // As operações CRUD básicas (save, findById, findAll, deleteById) são herdadas automaticamente!
    }




