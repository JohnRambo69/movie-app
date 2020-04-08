package com.rambosoftware.movieapp.controllers;

import com.rambosoftware.movieapp.models.*;
import com.rambosoftware.movieapp.recommender.Recommender;
import com.rambosoftware.movieapp.services.MovieService;
import com.rambosoftware.movieapp.services.RaterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rcmd")
public class RecommendationController {

    private final String VIEWS_RCMD_INDEX = "recommendation/index";
    private final String VIEWS_ERROR = "redirect:/error/error";
    private final String VIEWS_MOVIE_FIND = "movies/findMovies";
    private final String VIEWS_INDEX = "redirect:/home";


    MovieService movieService;
    RaterService raterService;

    public RecommendationController(MovieService movieService, RaterService raterService) {
        this.movieService = movieService;
        this.raterService = raterService;
    }

    @GetMapping(value = "/random")
    public String getRandom(Model model) {

        //Random random = new Random();

        List<RatingsList> ratingsToAdd = new ArrayList<>();
        //Set<Movie> moviesSet = movieService.findAll();
        List<Movie> moviesRandom = movieService.findRandom();
        //List<Movie> moviesRandom = moviesSet.stream().collect(Collectors.toList());
        for (int i = 0; i < 10; i++) {
//            int number = random.nextInt(moviesRandom.size());
//            Movie movie = moviesRandom.get(number);
//            while (movie == null) {
//                number = random.nextInt(moviesRandom.size());
            Movie movie = moviesRandom.get(i);
//            }
            ratingsToAdd.add(new RatingsList().builder().rate(0.0)
                    .movieId(movie.getMovieId())
                    .title(movie.getTitle()).build());
        }
        CreateRatingsList result = new CreateRatingsList();
        result.setRatingsLists(ratingsToAdd);

        if (result.getRatingsLists().size() < 1) {
            return VIEWS_ERROR;
        } else {

            model.addAttribute("moviesRatings", result);
            // model.addAttribute("ratings", createRatings);
            return VIEWS_RCMD_INDEX;
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute CreateRatingsList ratingList, Model model) {
        if (ratingList == null) {
            System.out.println("error 1");
            return VIEWS_ERROR;
        }
        List<RatingsList> list = ratingList.getRatingsLists();
        list.stream()
                .map(r -> r.getMovieId() + r.getTitle() + r.getRate())
                .forEach(System.out::println);

        List<Rating> ratings = new ArrayList<>();
        for (RatingsList rl : ratingList.getRatingsLists()) {
            ratings.add(new Rating().builder().movieId(rl.getMovieId()).rating(rl.getRate()).build());
        }

        Recommender r = new Recommender();

        Rater rater = new Rater();

        rater.setRatings(ratings);

        Set<Rater> ratersList = raterService.findAll();
        if (ratersList == null) {
            return VIEWS_ERROR;
        }
        Set<Movie> movies = movieService.findAll();

        List<Long> bestMovies = r.getSimilarRatingsByFilter(rater, ratersList, 15,
                2, movies);
        if (bestMovies == null) {
            return VIEWS_ERROR;
        }
        List<Movie> result = bestMovies.stream()
                .map(m -> movieService.findById(m))
                .collect(Collectors.toList());

        model.addAttribute("movies", result);
        return VIEWS_MOVIE_FIND;
    }
}

