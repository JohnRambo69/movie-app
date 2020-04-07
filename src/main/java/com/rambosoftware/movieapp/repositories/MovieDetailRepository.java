package com.rambosoftware.movieapp.repositories;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.MovieDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MovieDetailRepository extends CrudRepository<MovieDetails, Long> {

    public List<MovieDetails> findByCategoryLike(@Param("categoryName") String categoryName);

}
