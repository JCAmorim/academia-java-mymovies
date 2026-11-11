package pt.rumos.mymovies.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.rumos.mymovies.exception.MovieNotFoundException;
import pt.rumos.mymovies.service.MovieService;
import pt.rumos.mymovies.web.error.ApiError;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(movieController)
                .setControllerAdvice(new ApiError.ApiExceptionHandler())
                .build();
    }

    @Test
    void getById_WhenMovieDoesNotExist_ShouldReturn404AndMovieNotFoundCode()
            throws Exception {

        when(movieService.findById(99L))
                .thenThrow(new MovieNotFoundException(99L));

        mockMvc.perform(get("/api/movies/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.code").value("MOVIE_NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Movie not found: 99"))
                .andExpect(jsonPath("$.path").value("/api/movies/99"));
    }
}
