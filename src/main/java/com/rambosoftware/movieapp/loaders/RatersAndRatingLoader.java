package com.rambosoftware.movieapp.loaders;

import com.opencsv.CSVReader;
import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.Rater;
import com.rambosoftware.movieapp.models.Rating;
import com.rambosoftware.movieapp.services.MovieService;
import com.rambosoftware.movieapp.services.RaterService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;

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

        String csvFile = "src/main/resources/data/ratings.csv";

        try {
            FileReader filereader = new FileReader(csvFile);
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile), "Cp1252"));
            String[] nr;
            nr = csvReader.readNext();
            while ((nr = csvReader.readNext()) != null) {

                Long userId = Long.parseLong(nr[0]);
                Long movieId = Long.parseLong(nr[1]);
                Double rating = Double.parseDouble(nr[2]);

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

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Rating loaded.");

        }
    }
}
