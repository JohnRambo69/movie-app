package com.rambosoftware.movieapp.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="movies")
public class Movie implements Serializable {

    @Builder
    public Movie(Long movieId, String title, Integer year, String country
            , String genre, Integer minutes, String director, String poster, List<Rater> raters) {
        this.movieId = movieId;
        this.title = title;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.minutes = minutes;
        this.director = director;
        this.poster = poster;
        this.raters = raters;
    }





    @Id
    Long movieId;

    @Column(name="title")
    private String title;

    @Column(name="year")
    private Integer year;

    @Column(name="country")
    private String country;

    @Column(name="genre")
    private String genre;

    @Column(name="minutes")
    private Integer minutes;

    @Column(name="director")
    private String director;

    @Column(name="poster")
    private String poster;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie", cascade = {CascadeType.ALL})
    private List<Rater> raters = new ArrayList<>();

    public void addRater(Rater rater){
        raters.add(rater);
    }


}
