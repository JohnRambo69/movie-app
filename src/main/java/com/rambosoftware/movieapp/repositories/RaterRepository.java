package com.rambosoftware.movieapp.repositories;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.Rater;
import org.springframework.data.repository.CrudRepository;

public interface RaterRepository extends CrudRepository<Rater, Long> {
}
