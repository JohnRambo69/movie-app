package com.rambosoftware.movieapp.controllers;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/movie")
    public String movie(Model model){

        model.addAttribute("movies", movieService.findAll());
        return "pages/movie";
    }
}
