package com.rambosoftware.movieapp.controllers;

import com.rambosoftware.movieapp.models.*;
import com.rambosoftware.movieapp.recommender.Recommender;
import com.rambosoftware.movieapp.services.MovieService;
import com.rambosoftware.movieapp.services.RaterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

        Random random = new Random();
        //List<Movie> result = new ArrayList<>();

        List<RatingsList> ratingsToAdd = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long number = random.nextInt(8225) + 1;
            Movie movie = movieService.findById(number);
            while (movie == null) {
                number = random.nextInt(8225) + 1;
                movie = movieService.findById(number);
            }
            ratingsToAdd.add(new RatingsList().builder().rate(0.0)
                    .movieId(movie.getMovieId())
                    .title(movie.getName()).build());
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

    @GetMapping(value = "/getmovies")
    public String findByName(@RequestParam(value = "moviesRatings", required = false) CreateRatingsList ratingList, Model model) {
        if (ratingList == null) {
            System.out.println("error 1");
            return VIEWS_ERROR;
        }
        List<Rating> ratings = new ArrayList<>();
            for( RatingsList rl : ratingList.getRatingsLists()){
                ratings.add(new Rating().builder().movieId(rl.getMovieId()).rating(rl.getRate()).build());
            }

        Recommender r = new Recommender();

        Rater rater = new Rater();

        rater.setRatings(ratings);

        Set<Rater> ratersList = raterService.findAll();

        List<Long> bestMovies = r.getBestMovies(rater, ratersList, 20);

        List<Movie> result = bestMovies.stream()
                .map(m -> movieService.findById(m))
                .collect(Collectors.toList());
        if (ratersList == null || bestMovies == null || result == null) {
            System.out.println("error 2");
            return VIEWS_ERROR;
        } else {

            model.addAttribute("movies", result);
            return VIEWS_MOVIE_FIND;
        }

    }

//    @PostMapping("/save")
//    public String save(@ModelAttribute CreateRatingsList ratingList, Model model) {
//        if(ratingList == null){
//            System.out.println("Error");
//            return VIEWS_ERROR;
//        }else
//            System.out.println("OK");
//        List<RatingsList> list = ratingList.getRatingsLists();
//        list.stream().map(r -> r.getMovieId()).forEach(System.out::println);
//        return VIEWS_INDEX;
//    }

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
            if(ratersList == null){
                return VIEWS_ERROR;
            }
        List<Long> bestMovies = r.getBestMovies(rater, ratersList, 20);
            if(bestMovies == null){
                return VIEWS_ERROR;
            }
        List<Movie> result = bestMovies.stream()
                .map(m -> movieService.findById(m))
                .collect(Collectors.toList());

            model.addAttribute("movies", result);
            return VIEWS_MOVIE_FIND;
        }
    }

