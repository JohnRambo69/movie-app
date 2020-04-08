package com.rambosoftware.movieapp.services.springdatajpa;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.repositories.MovieRepository;
import com.rambosoftware.movieapp.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JpaMovieService implements MovieService {

private final MovieRepository movieRepository;

    public JpaMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
public Set<Movie> findAll(){
        Set<Movie> movies=new HashSet<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
        }

@Override
public Movie findById(Long aLong){
        return movieRepository.findById(aLong).orElse(null);
        }

@Override
public Movie save(Movie object){
        return movieRepository.save(object);
        }

@Override
public void delete(Movie object){
        movieRepository.delete(object);
        }

@Override
public void deleteById(Long aLong){
        movieRepository.deleteById(aLong);
        }

    @Override
    public List<Movie> findAllByTitleLike(String name) {
        return movieRepository.findAllByTitleLike(name);
    }

    @Override
    public List<Movie> findRandom(){
        return movieRepository.findRandom();
    };
}