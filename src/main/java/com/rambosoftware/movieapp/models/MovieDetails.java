package com.rambosoftware.movieapp.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="movies_details", indexes = {@Index(name = "MOVIE_INDEX", columnList = "movie_id")})
public class MovieDetails extends BaseEntity {

    @Builder
    public MovieDetails(Long id, String details, Long movieId) {
        super(id);
        this.details = details;
        this.movieId = movieId;
    }


    @Column(name="details")
    private String details;


    @Column(name="movie_id")
    Long movieId;

}
