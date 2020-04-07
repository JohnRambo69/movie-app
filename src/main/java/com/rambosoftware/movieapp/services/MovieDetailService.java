package com.rambosoftware.movieapp.services;

import com.rambosoftware.movieapp.models.MovieDetails;

import java.util.List;

public interface MovieDetailService extends CrudService<MovieDetails, Long> {

    List<MovieDetails> findByCategoryLike(String username);
}
