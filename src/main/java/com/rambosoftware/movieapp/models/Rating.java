package com.rambosoftware.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

/*    @Column(name="movie_id")
    private Long movieId;*/

    @Column(name="rating")
    private Double rating;

    @ManyToOne
    private Movie movie;
}
