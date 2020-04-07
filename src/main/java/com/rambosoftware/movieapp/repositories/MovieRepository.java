package com.rambosoftware.movieapp.repositories;

import com.rambosoftware.movieapp.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findAllByNameLike(String name);


}
