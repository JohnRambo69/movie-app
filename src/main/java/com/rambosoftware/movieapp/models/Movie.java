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
    public Movie( String name, Long movieId) {
        this.name = name;
        this.movieId = movieId;
    }

    @Id
    Long movieId;

    @Column(name="name")
    private String name;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie", cascade = {CascadeType.ALL})
    private List<Rater> raters = new ArrayList<>();

    public void addRater(Rater rater){
        raters.add(rater);
    }


}
