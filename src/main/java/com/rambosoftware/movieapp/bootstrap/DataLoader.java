package com.rambosoftware.movieapp.bootstrap;



import com.rambosoftware.movieapp.loaders.MovieDetailsLoader;
import com.rambosoftware.movieapp.loaders.MovieLoader;
import com.rambosoftware.movieapp.loaders.RatersAndRatingLoader;
import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.MovieDetails;
import com.rambosoftware.movieapp.models.Rater;
import com.rambosoftware.movieapp.models.Rating;
import com.rambosoftware.movieapp.services.MovieDetailService;
import com.rambosoftware.movieapp.services.MovieService;
import com.rambosoftware.movieapp.services.RaterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class DataLoader implements CommandLineRunner {

    private final MovieService movieService;
    private final MovieDetailService movieDetailService;
    private final RaterService raterService;

    private final MovieLoader movieLoader;
    private final MovieDetailsLoader movieDetailsLoader;
    private final RatersAndRatingLoader ratersAndRatingLoader;

    public DataLoader(MovieService movieService, MovieDetailService movieDetailService
            , RaterService raterService, MovieLoader movieLoader, MovieDetailsLoader movieDetailsLoader
            , RatersAndRatingLoader ratersAndRatingLoader) {
        this.movieService = movieService;
        this.movieDetailService = movieDetailService;
        this.raterService = raterService;
        this.movieLoader = movieLoader;
        this.movieDetailsLoader = movieDetailsLoader;
        this.ratersAndRatingLoader = ratersAndRatingLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        //movieDetailsLoader.loadPosters();
        // loadData();

    }

    private void loadData() {

        System.out.println("Checking Database...");
// ########### LOAD MOVIES ####################
        int moviesSize = movieService.findAll().size();
        if (moviesSize == 0) {
            System.out.println("Movie database is empty.");
            movieLoader.loadMovies();
        } else {
            System.out.println("Movie database already loaded.");
            System.out.println("Movies in database: " + moviesSize);
        }
//// ################## LOAD RATERS AND RATING ###########################
        int ratingSize = raterService.findAll().size();
        if (ratingSize == 0) {
            System.out.println("Rating Database is empty.");
            ratersAndRatingLoader.loadRatersAndRatings();
        } else {
            System.out.println("Ratings database already loaded.");
            System.out.println("Raters in database: " + ratingSize);
        }
// ################# LOAD POSTERS AND DETAILS ##################
        int detailsSize = movieDetailService.findAll().size();
        if (detailsSize == 0) {
            System.out.println("Details Database is empty.");
            movieDetailsLoader.loadDetails();
            movieDetailsLoader.loadPosters();
        } else {
            System.out.println("Details database already loaded.");
            System.out.println("Details in database: " + detailsSize);
        }

  }
    }


