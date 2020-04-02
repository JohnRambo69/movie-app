package com.rambosoftware.movieapp.loaders;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.MovieDetails;
import com.rambosoftware.movieapp.services.MovieDetailService;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

@Component
public class MovieDetailsLoader {

    private MovieDetailService movieDetailService;
    private MovieService movieService;

    public MovieDetailsLoader(MovieDetailService movieDetailService, MovieService movieService) {
        this.movieDetailService = movieDetailService;
        this.movieService = movieService;
    }


    public void loadPosters(){

        System.out.println("Starting Load movies posters....");

        String csvFile = "src/main/resources/data/movies_metadata.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",\"";
        Set<Movie> movieList = movieService.findAll();
        String prefix = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/";
        String movieName = "";
        String url = null;
        try {

            br = new BufferedReader(new FileReader(csvFile));

            br.readLine();

            for(Movie m : movieList){
                movieName = m.getName();
                while ((line = br.readLine()) != null) {
                    String[] csvLine = line.split(cvsSplitBy);
                    for (int j = 0; j < csvLine.length; j++) {
                        if (csvLine[j].contains(movieName)) {
                            for (int k = 0; k < csvLine.length; k++) {
                                if (csvLine[k].contains(".jpg")) {
                                    url = csvLine[k];
                                    break;
                                }
                            }
                        }
                    }
                }
                if(url != null) {
                    String getUrl = prefix + url.substring(url.length() - 31);
                    MovieDetails md = movieDetailService.findById(m.getMovieId());
                    md.setUrl(getUrl);
                    movieDetailService.save(md);
                }else{
                    // System.out.println("Not Found.");
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Loading done.");
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadDetails(){

        System.out.println("Starting Load movies details....");

        String csvFile = "src/main/resources/data/movie.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",\"";
        Long year = 0L;

        try {

            br = new BufferedReader(new FileReader(csvFile));

            br.readLine();
            while ((line = br.readLine()) != null){
                String[] csvLine = line.split(cvsSplitBy);

                Long id = Long.parseLong(csvLine[0]);

                try {
                    year = Long.parseLong(csvLine[1].substring(csvLine[1].length() - 6, csvLine[1].length() - 2).trim());
                }catch (Exception e){
                    year = 0L;
                }
                String[] category = csvLine[2].split("[|]");
                String readyCategory = "";
                for(String s : category){
                    readyCategory = readyCategory + " " + s;
                }
                readyCategory = readyCategory.replace("\"", "").trim();

                MovieDetails movieDetails = new MovieDetails();
                movieDetails.setMovieId(id);
                movieDetails.setCategory(readyCategory);
                movieDetails.setYear(year);
                movieDetailService.save(movieDetails);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
