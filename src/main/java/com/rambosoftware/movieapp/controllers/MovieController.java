package com.rambosoftware.movieapp.controllers;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.repositories.MovieRepository;
import com.rambosoftware.movieapp.services.MovieDetailService;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private final String VIEWS_MOVIE_DETAILS = "movies/movieDetails";
    private final String VIEWS_MOVIE_FIND = "movies/findMovies";
    private final String VIEWS_ERROR = "errors/notFound";

    MovieService movieService;
    MovieDetailService movieDetailService;

    public MovieController(MovieService movieService, MovieDetailService movieDetailService) {
        this.movieService = movieService;
        this.movieDetailService = movieDetailService;
    }

    @GetMapping(value = "/find")
    public  String findByName(@RequestParam(value="name",required=false) String name, Model model) {
        if (name == null) {
            return VIEWS_ERROR;
        }
        List<Movie> result = movieService.findAllByNameLike("%" + name + "%");
        if(result == null){
            return VIEWS_ERROR;
        }else {
            model.addAttribute("movies", result);
            return VIEWS_MOVIE_FIND;
        }
    }


    @GetMapping("/{movieId}")
    public ModelAndView showOwner(@PathVariable Long movieId){
        ModelAndView mav = new ModelAndView(VIEWS_MOVIE_DETAILS);
        mav.addObject(movieService.findById(movieId));
        mav.addObject(movieDetailService.findById(movieId));
        return mav;
    }

}
