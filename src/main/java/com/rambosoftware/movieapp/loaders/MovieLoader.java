package com.rambosoftware.movieapp.loaders;

import com.opencsv.CSVReader;
import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class MovieLoader {

    private MovieService movieService;

    public MovieLoader(MovieService movieService) {
        this.movieService = movieService;
    }


    public void loadMovies() {
        System.out.println("Starting Load movies....");

        String csvFile = "src/main/resources/data/ratedmoviesfull.csv";

        try {
            FileReader filereader = new FileReader(csvFile);
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile), "Cp1252"));
            String[] nr;

            nr = csvReader.readNext();
            while ((nr = csvReader.readNext()) != null) {
               if(nr[5].length() > 250){
                   nr[5] = nr[5].substring(0,250);
               }
                Movie movie = new Movie().builder().movieId(Long.valueOf(nr[0])).title(nr[1])
                        .year(Integer.valueOf(nr[2])).country(nr[3]).genre(nr[4])
                        .director(nr[5]).minutes(Integer.valueOf(nr[6])).poster(nr[7]).build();
                movieService.save(movie);
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
