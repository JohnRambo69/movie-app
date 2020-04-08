package com.rambosoftware.movieapp.models;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class RatingsList {

    @Builder
    public RatingsList(Double rate, String title, Long movieId) {
        this.rate = rate;
        this.title = title;
        this.movieId = movieId;
    }

    private Double rate;
    private String title;
    private Long movieId;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
