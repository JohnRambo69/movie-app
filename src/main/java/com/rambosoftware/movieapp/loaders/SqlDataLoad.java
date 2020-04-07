//package com.rambosoftware.movieapp.loaders;
//
//import com.rambosoftware.movieapp.models.Movie;
//import com.rambosoftware.movieapp.models.MovieDetails;
//import com.rambosoftware.movieapp.models.Rater;
//import com.rambosoftware.movieapp.models.Rating;
//import com.rambosoftware.movieapp.recommender.Recommender;
//import com.rambosoftware.movieapp.repositories.RatingRepository;
//import com.rambosoftware.movieapp.services.MovieDetailService;
//import com.rambosoftware.movieapp.services.MovieService;
//import com.rambosoftware.movieapp.services.RaterService;
//import com.rambosoftware.movieapp.services.RatingService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//@Component
//public class SqlDataLoad implements CommandLineRunner {

//    private final RaterService raterService;
//    private final MovieService movieService;
//    private final MovieDetailService movieDetailService;
//    private final RatingService ratingService;
//
//    public SqlDataLoad(RaterService raterService, MovieService movieService, MovieDetailService movieDetailService, RatingService ratingService) {
//        this.raterService = raterService;
//        this.movieService = movieService;
//        this.movieDetailService = movieDetailService;
//        this.ratingService = ratingService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Long sum = ratingService.sumRatingsByMovieId(1L);
//        System.out.println(sum);
//        System.out.println("Done.");
//    }
//    }
//        System.out.println("Im alieve !!");
//        Recommender r = new Recommender();
//        Rater rater1 = raterService.findById(587L);
//        Set<Rater> ratersList = raterService.findAll();
//        Set<Movie> movies = movieService.findAll();
//        Map<Rater, Double> result = r.getSimilarities(rater1, ratersList);
//
//        List<Long> bestMovies = r.getBestMovies(rater1,ratersList, 20);
//        bestMovies.stream().map(m -> movieService.findById(m).getName())
//                .forEach(System.out::println);


//        Iterator<Rater> raterIterator = result.keySet().iterator();
//        Rater rat = raterIterator.next();
//        List<Rating> ratings = rat.getRatings();
//        ratings.stream().map(m -> movieService.findById(m.getMovieId()).getName())
//                .forEach(System.out::println);
//        System.out.println(rat.getRatings().size());

//        result.entrySet().stream()
//                .map(e -> "RaterID:" + e.getKey().getUserId() + "\t DotVal: " + e.getValue())
//                .forEach(System.out::println);

//        List<Long> filterResult = r.getSimilarRatingsByFilter(rater1, ratersList, 1, 1, movies);
//        filterResult.stream()
//              .forEach(System.out::println);
//
//        System.out.println("Done.");
//   }
//
//}





