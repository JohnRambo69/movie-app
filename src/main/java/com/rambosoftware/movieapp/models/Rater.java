package com.rambosoftware.movieapp.models;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
@Table(name = "raters")
public class Rater implements Serializable {

    @Id
    private Long userId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rater", cascade = {CascadeType.ALL})
    private List<Rating> ratings = new ArrayList<>();

}
