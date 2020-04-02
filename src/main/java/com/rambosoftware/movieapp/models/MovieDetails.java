package com.rambosoftware.movieapp.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="movies_details")
public class MovieDetails implements Serializable {

    @Builder
    public MovieDetails(Long movieId, String details, String url, String category, Long year) {
        this.movieId = movieId;
        this.details = details;
        this.url = url;
        this.category = category;
        this.year = year;
    }



    @Id
    Long movieId;

    @Column(name="details")
    private String details;

    @Column(name="url")
    private String url;

    @Column(name="category")
    private String category;

    @Column(name="year")
    private Long year;

}
