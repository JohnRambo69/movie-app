package com.rambosoftware.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "raters")
public class Rater extends BaseEntity {

    @Column(name="user_id")
    private Long userId;

    @ManyToOne
    private Movie movie;

/*    @OneToMany(cascade = CascadeType.ALL, mappedBy ="rater")
    private Set<Rating> ratings = new HashSet<>();*/
}
