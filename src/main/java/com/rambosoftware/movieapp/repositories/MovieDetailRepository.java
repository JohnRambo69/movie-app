package com.rambosoftware.movieapp.repositories;

import com.rambosoftware.movieapp.models.MovieDetails;
import org.springframework.data.repository.CrudRepository;

public interface MovieDetailRepository extends CrudRepository<MovieDetails, Long> {
}
