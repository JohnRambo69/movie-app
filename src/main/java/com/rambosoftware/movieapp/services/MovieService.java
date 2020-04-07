package com.rambosoftware.movieapp.services;

import com.rambosoftware.movieapp.models.Movie;

import java.util.List;

public interface MovieService extends CrudService<Movie, Long> {

    Movie findById(Long id);

    List<Movie> findAllByNameLike(String name);

}
