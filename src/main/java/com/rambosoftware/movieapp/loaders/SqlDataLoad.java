package com.rambosoftware.movieapp.loaders;
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

import com.opencsv.CSVReader;
import com.rambosoftware.movieapp.models.Movie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SqlDataLoad {


    public static void main(String[] args){
        System.out.println("Starting Load movies....");

        String csvFile = "src/main/resources/data/ratedmoviesfull.csv";


        try {
            FileReader filereader = new FileReader(csvFile);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;


            for(int i = 0; i < 5; i++){
                nextRecord = csvReader.readNext();
                for (String cell : nextRecord) {
                    System.out.println(cell + "\t");
                }


            }
        } catch (FileNotFoundException e) {
            System.out.println("file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("other");
            e.printStackTrace();
        } finally {
            System.out.println("Movie loaded.");

            }
        }

    }




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





