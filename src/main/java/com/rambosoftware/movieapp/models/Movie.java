package com.rambosoftware.movieapp.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="movies")
public class Movie extends BaseEntity {

    @Builder
    public Movie(Long id, String name, Long movieId, String posterUrl, Set<Rating> ratings, Set<Rater> raters) {
        super(id);
        this.name = name;
        this.movieId = movieId;
        this.posterUrl = posterUrl;
        this.ratings = ratings;
        this.raters = raters;
    }


    @Column(name="name")
    private String name;

    @Column(name="movie_id")
    @PrimaryKeyJoinColumn
    private Long movieId;

    @Column(name="poster_url")
    private String posterUrl;

    @OneToMany

    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private Set<Rater> raters = new HashSet<>();

}
