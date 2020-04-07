package com.rambosoftware.movieapp.services.springdatajpa;

import com.rambosoftware.movieapp.models.Rating;
import com.rambosoftware.movieapp.repositories.RatingRepository;
import com.rambosoftware.movieapp.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JpaRatingService implements RatingService {

    private final RatingRepository ratingRepository;

    public JpaRatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Long sumRatingsByMovieId(Long movieId) {
        return ratingRepository.sumRatingsByMovieId(movieId);
    }

    @Override
    public Set<Rating> findAll() {
        Set<Rating> ratings = new HashSet<>();
        ratingRepository.findAll().forEach(ratings::add);
        return ratings;
    }

    @Override
    public Rating findById(Long aLong){
        return ratingRepository.findById(aLong).orElse(null);
    }

    @Override
    public Rating save(Rating object) {
        return ratingRepository.save(object);
    }

    @Override
    public void delete(Rating object) {
        ratingRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ratingRepository.deleteById(aLong);
    }
}
