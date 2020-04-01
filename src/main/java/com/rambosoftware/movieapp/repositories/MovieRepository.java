package com.rambosoftware.movieapp.repositories;

import com.rambosoftware.movieapp.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
