package com.bulbul.unittest.controller;

import com.bulbul.unittest.model.Movie;
import com.bulbul.unittest.service.MovieService;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    private Movie avatarMovie;
    private Movie titanicMovie;


    @BeforeEach
    void init() {
        avatarMovie = new Movie();
        avatarMovie.setId(1L);
        avatarMovie.setName("Avatar");
        avatarMovie.setGenera("Action");
        avatarMovie.setReleaseDate(LocalDate.of(1999, Month.APRIL, 22));

        titanicMovie = new Movie();
        avatarMovie.setId(2L);
        titanicMovie.setName("Titanic");
        titanicMovie.setGenera("Romance");
        titanicMovie.setReleaseDate(LocalDate.of(1997, Month.DECEMBER, 19));
    }


    @Test
    void shouldCreateMovie() throws Exception {
        when(movieService.save(any(Movie.class))).thenReturn(avatarMovie);

        this.mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avatarMovie)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(avatarMovie.getId().intValue())))
                .andExpect(jsonPath("$.name", is(avatarMovie.getName())))
                .andExpect(jsonPath("$.genera", is(avatarMovie.getGenera())))
                .andExpect(jsonPath("$.releaseDate", is(avatarMovie.getReleaseDate().toString())));
    }

    @Test
    void shouldReadAllMovies() throws Exception{
        List<Movie> movies = new ArrayList<>();
        movies.add(avatarMovie);
        movies.add(titanicMovie);

        when(movieService.getAllMovies()).thenReturn(movies);

        this.mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(movies.size())));
    }

    @Test
    void shouldReadMovieById() throws Exception{
        when(movieService.getMovieById(anyLong())).thenReturn(avatarMovie);

        this.mockMvc.perform(get("/movies/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(avatarMovie.getId().intValue())))
                .andExpect(jsonPath("$.name", is(avatarMovie.getName())))
                .andExpect(jsonPath("$.genera", is(avatarMovie.getGenera())))
                .andExpect(jsonPath("$.releaseDate", is(avatarMovie.getReleaseDate().toString())));
    }

    @Test
    void shouldDeleteMovieById() throws Exception{
        doNothing().when(movieService).deleteMovie(anyLong());

        this.mockMvc.perform(delete("/movies/{id}",1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void update() throws Exception{
        when(movieService.updateMovie(any(Movie.class),anyLong())).thenReturn(avatarMovie);

        this.mockMvc.perform(put("/movies/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avatarMovie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(avatarMovie.getId().intValue())))
                .andExpect(jsonPath("$.name", is(avatarMovie.getName())))
                .andExpect(jsonPath("$.genera", is(avatarMovie.getGenera())));
    }
}