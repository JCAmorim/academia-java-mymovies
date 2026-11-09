package pt.rumos.mymovies.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.rumos.mymovies.dto.MovieResponse;
import pt.rumos.mymovies.exception.MovieNotFoundException;
import pt.rumos.mymovies.model.Movie;
import pt.rumos.mymovies.repository.MovieRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void findById_WhenMovieExists_ShouldReturnMovieResponse() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Interstellar");
        movie.setDirector("Christopher Nolan");
        movie.setYear(2014);
        movie.setRating(8.6);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        MovieResponse response = movieService.findById(1L);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Interstellar", response.title());
        verify(movieRepository, times(1)).findById(1L);
    }

    @Test
    void findById_WhenMovieDoesNotExist_ShouldThrowMovieNotFoundException() {
        when(movieRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.findById(99L));
        verify(movieRepository, times(1)).findById(99L);
    }
}

