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
import pt.rumos.mymovies.web.error.ApiExceptionHandler;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void getById_WhenNotFound_ShouldReturn404AndApiError() throws Exception {
        when(movieService.findById(99L)).thenThrow(new MovieNotFoundException(99L));

        mockMvc.perform(get("/api/movies/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("MOVIE_NOT_FOUND"))
                .andExpect(jsonPath("$.status").value(404));
    }
}
