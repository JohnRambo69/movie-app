package com.rambosoftware.movieapp.repositories;

import com.rambosoftware.movieapp.models.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findAllByTitleLike(String name);

    @Query(value = "SELECT * FROM  movies m ORDER BY RAND() LIMIT 20", nativeQuery = true)
    List<Movie> findRandom();


}
