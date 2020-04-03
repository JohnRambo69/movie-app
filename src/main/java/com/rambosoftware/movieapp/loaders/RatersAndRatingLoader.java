package com.rambosoftware.movieapp.loaders;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.Rater;
import com.rambosoftware.movieapp.models.Rating;
import com.rambosoftware.movieapp.services.MovieService;
import com.rambosoftware.movieapp.services.RaterService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class RatersAndRatingLoader  {
    private MovieService movieService;
    private RaterService raterService;

    public RatersAndRatingLoader(MovieService movieService, RaterService raterService) {
        this.movieService = movieService;
        this.raterService = raterService;
    }

    public void loadRatersAndRatings(){


        System.out.println("Starting Load Ratings....");

        String csvFile = "src/main/resources/data/rating.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            int maxLoad = 100000;
            while (maxLoad >= 0) {

                line = br.readLine();
                String[] csvLine = line.split(cvsSplitBy);
                Long userId = Long.parseLong(csvLine[0]);
                Long movieId = Long.parseLong(csvLine[1]);
                Double rating = Double.parseDouble(csvLine[2]);

                Rater rater = new Rater();
                rater.setUserId(userId);
                Movie movie = movieService.findById(movieId);
                rater.setMovie(movie);
                movie.getRaters().add(rater);
                movieService.save(movie);

                Rating ratingDb = new Rating();
                ratingDb.setRating(rating);
                ratingDb.setRater(rater);
                ratingDb.setMovieId(movieId);
                rater.getRatings().add(ratingDb);
                raterService.save(rater);

                maxLoad--;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Rating loaded.");
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace(); }
            }
        }
    }
}
