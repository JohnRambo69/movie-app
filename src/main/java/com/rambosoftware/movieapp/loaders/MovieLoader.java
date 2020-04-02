package com.rambosoftware.movieapp.loaders;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.services.MovieDetailService;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class MovieLoader {

    private MovieService movieService;
    private MovieDetailService movieDetailService;

    public MovieLoader(MovieService movieService, MovieDetailService movieDetailService) {
        this.movieService = movieService;
        this.movieDetailService = movieDetailService;
    }


    public void loadMovies(){

        System.out.println("Starting Load movies....");

        String csvFile = "src/main/resources/data/movie.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",\"";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] csvLine = line.split(cvsSplitBy);
                Long id = Long.parseLong(csvLine[0]);
                String title = csvLine[1].substring(0, csvLine[1].length() - 7);
                String[] category = csvLine[2].split("|") ;

                Movie movie = Movie.builder().movieId(id).name(title).build();
                movieService.save(movie);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Movie loaded.");
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
