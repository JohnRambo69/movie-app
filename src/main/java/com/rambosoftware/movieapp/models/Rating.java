package com.rambosoftware.movieapp.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

    @Builder
    public Rating(Long id, Long movieId, Double rating) {
        super(id);
        this.movieId = movieId;
        this.rating = rating;
    }

    public Rating(Long movieId, Double rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    @Column(name="movie_id")
    private Long movieId;

    @Column(name="rating")
    private Double rating;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private Rater rater;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Rating)) {
            return false;
        }

        Rating r = (Rating) o;

        return r.getMovieId() == this.getMovieId();
    }

}


