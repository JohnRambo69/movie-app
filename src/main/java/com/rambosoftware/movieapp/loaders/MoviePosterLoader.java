package com.rambosoftware.movieapp.loaders;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

@Component
public class MoviePosterLoader {


    private MovieService movieService;

    public MoviePosterLoader(MovieService movieService) {
        this.movieService = movieService;
    }

    public void loadPosters() {

        System.out.println("Starting Load movies posters....");

        String csvFile = "src/main/resources/data/movies_metadata.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",\"";
        Set<Movie> movieList = movieService.findAll();
        String prefix = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
        String movieName = "";
        String url = null;
        try {
            for (Movie m : movieList) {
                br = new BufferedReader(new FileReader(csvFile));
                br.readLine();
                movieName = m.getTitle();
                Movie md = movieService.findById(m.getMovieId());
                while ((line = br.readLine()) != null) {
                    url = null;
                    if (line.indexOf(movieName) > -1) {
                        try {
                            url = line.substring(line.indexOf(".jpg") - 28, line.indexOf(".jpg") + 4);
                        } catch (Exception e) {
                        } finally {
                            String getUrl = null;
                            if (url != null) {
                                getUrl = prefix + fixUrl(url);
                            } else {
                                //System.out.println("Not found.");
                            }

                            md.setPoster(getUrl);
                            movieService.save(md);
                            br.close();
                            break;
                        }
                    }
                }

            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error");
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


    private String fixUrl(String url) {
        if (url.indexOf("'") == 0) {
            String result = url.substring(1);
            return result;
        } else {
            return url;
        }
    }

}
