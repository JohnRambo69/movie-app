package com.rambosoftware.movieapp.services.springdatajpa;

import com.rambosoftware.movieapp.models.Rater;
import com.rambosoftware.movieapp.repositories.RaterRepository;
import com.rambosoftware.movieapp.services.RaterService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JpaRaterService implements RaterService {

    private final RaterRepository raterRepository;

    public JpaRaterService(RaterRepository raterRepository) {
        this.raterRepository = raterRepository;
    }

    @Override
    public Set<Rater> findAll() {
        Set<Rater> movies = new HashSet<>();
        raterRepository.findAll().forEach(movies::add);
        return movies;
    }

    @Override
    public Rater findById(Long aLong) {
        return raterRepository.findById(aLong).orElse(null);
    }

    @Override
    public Rater save(Rater object) {
        return raterRepository.save(object);
    }

    @Override
    public void delete(Rater object) {
        raterRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        raterRepository.deleteById(aLong);
    }

}
