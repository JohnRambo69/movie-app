package com.rambosoftware.movieapp.bootstrap;



import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.MovieDetails;
import com.rambosoftware.movieapp.services.MovieDetailService;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class DataLoader implements CommandLineRunner {

    private final MovieService movieService;
    private final MovieDetailService movieDetailService;

    public DataLoader(MovieService movieService, MovieDetailService movieDetailService) {
        this.movieService = movieService;
        this.movieDetailService = movieDetailService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println();

   int count = movieService.findAll().size();
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {

        long movieId = 22;

        MovieDetails movieDetail = MovieDetails.builder().details("bajka").movieId(movieId).build();
        Movie movie = Movie.builder().movieId(movieId).name("ToyStory").build();



                movieService.save(movie);
              //  movieDetailService.save(movieDetail);


        System.out.println("Loaded Movies...");
    }
}

