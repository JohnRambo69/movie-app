package com.rambosoftware.movieapp.services.springdatajpa;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.MovieDetails;
import com.rambosoftware.movieapp.repositories.MovieDetailRepository;
import com.rambosoftware.movieapp.services.MovieDetailService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JpaMovieDetailsService implements MovieDetailService {

    private final MovieDetailRepository movieDetailRepository;

    public JpaMovieDetailsService(MovieDetailRepository movieDetailRepository) {
        this.movieDetailRepository = movieDetailRepository;
    }

    @Override
    public Set<MovieDetails> findAll(){
        Set<MovieDetails> movieDetails = new HashSet<>();
        movieDetailRepository.findAll().forEach(movieDetails::add);
        return movieDetails;
    }

    @Override
    public MovieDetails findById(Long aLong){
        return movieDetailRepository.findById(aLong).orElse(null);
    }

    @Override
    public MovieDetails save(MovieDetails object){
        return movieDetailRepository.save(object);
    }

    @Override
    public void delete(MovieDetails object){
        movieDetailRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong){
        movieDetailRepository.deleteById(aLong);
    }


}
