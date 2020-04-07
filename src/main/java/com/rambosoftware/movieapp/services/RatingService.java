package com.rambosoftware.movieapp.services;

import com.rambosoftware.movieapp.models.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingService extends CrudService<Rating, Long> {


    Long sumRatingsByMovieId(Long movieId);
}
