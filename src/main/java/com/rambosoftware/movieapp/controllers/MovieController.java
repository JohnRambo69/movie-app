package com.rambosoftware.movieapp.controllers;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private final String VIEWS_MOVIE_DETAILS = "movies/movieDetails";
    private final String VIEWS_MOVIE_FIND = "movies/findMovies";
    private final String VIEWS_ERROR = "redirect:/error/error";

    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/find")
    public String findByName(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name == null) {
            return VIEWS_ERROR;
        }
        List<Movie> result = movieService.findAllByTitleLike("%" + name + "%");
        if (result == null) {
            return VIEWS_ERROR;
        } else {
            model.addAttribute("movies", result);
            return VIEWS_MOVIE_FIND;
        }
    }


    @GetMapping("/{movieId}")
    public String findByName(@PathVariable Long movieId, Model model) {
        if (movieId == null) {
            return VIEWS_ERROR;
        }
        Movie movie = movieService.findById(movieId);
        if (movie == null) {
            return VIEWS_ERROR;
        } else {
            model.addAttribute("movie", movie);
            return VIEWS_MOVIE_DETAILS;
        }
    }

}
