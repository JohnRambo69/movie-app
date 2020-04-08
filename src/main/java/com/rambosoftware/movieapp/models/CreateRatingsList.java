package com.rambosoftware.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Setter
@Getter
public class CreateRatingsList {

       private List<RatingsList> ratingsLists;

    public CreateRatingsList() {
        this.ratingsLists = new ArrayList<>();
    }

    public List<RatingsList> getRatingsLists() {
        return ratingsLists;
    }

    public void setRatingsLists(List<RatingsList> ratingsLists) {
        this.ratingsLists = ratingsLists;
    }
}
