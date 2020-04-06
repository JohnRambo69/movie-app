package com.rambosoftware.movieapp.loaders;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.MovieDetails;
import com.rambosoftware.movieapp.services.MovieDetailService;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.stereotype.Component;

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
                movieName = fixMovieTitle(m.getName());
                MovieDetails md = movieDetailService.findById(m.getMovieId());
                while ((line = br.readLine()) != null && md.getUrl() == null) {
                    url = null;
                    if (line.indexOf(movieName) > -1) {
                        try {
                            url = line.substring(line.indexOf(".jpg") - 28, line.indexOf(".jpg") + 4);
                        } catch (Exception e) {
                        } finally {
                            String getUrl = null;
                            if(url != null) {
                                getUrl = prefix + fixUrl(url);
                            }else{
                                //System.out.println("Not found.");
                            }

                            md.setUrl(getUrl);
                            movieDetailService.save(md);
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

    private String fixMovieTitle(String movie) {
        String movieTitle = movie.trim();
        if (movieTitle.indexOf("The") > -1) {
            try {
                String tempTitle = movieTitle.substring(0, movieTitle.length() - 5);
                movieTitle = "The " + tempTitle;
                return movieTitle;
            }catch (Exception e){
                return  movieTitle;
            }
        } else if (movieTitle.indexOf(", A") > -1) {
            try {
                String tempTitle = movieTitle.substring(0, movieTitle.length() - 3);
                movieTitle = "A " + tempTitle;
            }catch (Exception e){
                return movieTitle;
            }
        }
        return movieTitle;

    }

    private String fixUrl(String url) {
        if (url.indexOf("'") == 0) {
            String result = url.substring(1);
            return result;
        } else {
            return url;
        }
    }

    public void loadDetails() {

        System.out.println("Starting Load movies details....");

        String csvFile = "src/main/resources/data/movie.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",\"";
        Long year = 0L;

        try {

            br = new BufferedReader(new FileReader(csvFile));

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] csvLine = line.split(cvsSplitBy);

                Long id = Long.parseLong(csvLine[0]);

                try {
                    year = Long.parseLong(csvLine[1].substring(csvLine[1].length() - 6, csvLine[1].length() - 2).trim());
                } catch (Exception e) {
                    year = 0L;
                }
                String[] category = csvLine[2].split("[|]");
                String readyCategory = "";
                for (String s : category) {
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
