package com.rambosoftware.movieapp.repositories;

import com.rambosoftware.movieapp.models.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    @Query(value = "SELECT sum(r.rating) FROM ratings r WHERE r.movie_id = :movieId", nativeQuery = true)
    Long sumRatingsByMovieId(@Param("movieId") Long movieId);
}
