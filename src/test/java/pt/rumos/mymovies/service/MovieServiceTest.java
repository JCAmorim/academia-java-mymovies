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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void findById_WhenMovieExists_ShouldReturnMovieResponse() {
        // Arrange
        Movie movie = new Movie(
                1L,
                "The Matrix",
                "Wachowski Sisters",
                1999
        );

        when(movieRepository.findById(1L))
                .thenReturn(Optional.of(movie));

        // Act
        MovieResponse response = movieService.findById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("The Matrix", response.title());
        assertEquals("Wachowski Sisters", response.director());
        assertEquals(1999, response.year());
    }

    @Test
    void findById_WhenMovieDoesNotExist_ShouldThrowMovieNotFoundException() {
        // Arrange
        when(movieRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                MovieNotFoundException.class,
                () -> movieService.findById(99L)
        );
    }
}