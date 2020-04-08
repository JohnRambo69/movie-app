package com.rambosoftware.movieapp.bootstrap;


import com.rambosoftware.movieapp.loaders.MovieLoader;
import com.rambosoftware.movieapp.loaders.MoviePosterLoader;
import com.rambosoftware.movieapp.loaders.RatersAndRatingLoader;
import com.rambosoftware.movieapp.services.MovieService;
import com.rambosoftware.movieapp.services.RaterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final MovieService movieService;
    private final RaterService raterService;

    private final MovieLoader movieLoader;
    private final RatersAndRatingLoader ratersAndRatingLoader;
    private final MoviePosterLoader moviePosterLoader;

    public DataLoader(MovieService movieService, RaterService raterService, MovieLoader movieLoader
            , RatersAndRatingLoader ratersAndRatingLoader, MoviePosterLoader moviePosterLoader) {
        this.movieService = movieService;
        this.raterService = raterService;
        this.movieLoader = movieLoader;
        this.ratersAndRatingLoader = ratersAndRatingLoader;
        this.moviePosterLoader = moviePosterLoader;
    }

    @Override
    public void run(String... args) throws Exception {
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
        int detailsSize = movieService.findAll().size();
        if (detailsSize == 0) {
            System.out.println("Details Database is empty.");
            moviePosterLoader.loadPosters();
        } else {
            System.out.println("Details database already loaded.");
            System.out.println("Details in database: " + detailsSize);
        }

    }
}


