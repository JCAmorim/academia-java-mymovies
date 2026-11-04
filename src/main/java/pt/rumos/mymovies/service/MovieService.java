package pt.rumos.mymovies.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.rumos.mymovies.dto.*;
import pt.rumos.mymovies.model.Movie;
import pt.rumos.mymovies.repository.MovieRepository;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }



    @Transactional(readOnly = true)
    public List<MovieResponse> findAll() {
        // TODO: Buscar todos os filmes do repository e mapear para MovieResponse
        return repository.findAll()
                .stream()
                .map(MovieResponse::fromDomain)
                .toList();
    }



        // TODO: Buscar por id no repository e converter para Optional<MovieResponse>
        @Transactional(readOnly = true)
        public Optional<MovieResponse> findById(Long id) {
            return repository
                    .findById(id)
                    .map(MovieResponse::fromDomain);
        }


        // TODO: Converter MovieRequest para a Entidade Movie, salvar no repository e retornar MovieResponse
        @Transactional
        public MovieResponse create(MovieRequest request) {

            Movie movie = new Movie();
            movie.setTitle(request.title());
            movie.setDirector(request.director());
            movie.setYear(request.year());
            Movie savedMovie = repository.save(movie);
            return MovieResponse.fromDomain(savedMovie);
        }



        // TODO: Buscar filme por id; se existir, atualizar a nota, salvar e retornar  Optional<MovieResponse>
        @Transactional
        public Optional<MovieResponse> updateRating(
                Long id,
                RatingUpdateRequest request) {

            return repository.findById(id)
                    .map(movie -> {
                        movie.setRating(request.rating());

                        Movie updatedMovie = repository.save(movie);

                        return MovieResponse.fromDomain(updatedMovie);
                    });
        }



        // TODO: Verificar se id existe; se sim, eliminar no repository e retornar  true; senão false
        @Transactional
        public boolean delete(Long id) {

            if (!repository.existsById(id)) {
                return false;
            }
            repository.deleteById(id);
            return true;
        }

    @Transactional(readOnly = true)
    public List<MovieResponse> findAll(String title) {

        List<Movie> movies;

        if (title != null && !title.isBlank()) {
            movies = repository.findByTitleContainingIgnoreCase(title);
        } else {
            movies = repository.findAll();
        }

        return movies.stream()
                .map(MovieResponse::fromDomain)
                .toList();
    }



    }

