package com.rambosoftware.movieapp.recommender;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.Rater;
import com.rambosoftware.movieapp.models.Rating;

import java.util.*;

public class Recommender {

    private static double dotProduct(Rater me, Rater other) {
        double product = 0.0;
        List<Rating> meIDs = me.getRatings();
        List<Rating> otherIDs = other.getRatings();

        for (Rating meID : meIDs) {
            if (otherIDs.contains(meID)) {
                product = product + (meID.getRating() - 5)
                        * (otherIDs.get(otherIDs.indexOf(meID)).getRating() - 5);

            }
        }
        return product;
    }

    private Map<Rater, Double> getSimilarities(Rater rater, Set<Rater> raterList) {
        Map<Rater, Double> movieAndDot = new HashMap<>();
        for (Rater r : raterList) {
            if (!r.getUserId().equals(rater.getUserId())) {

                double dot = dotProduct(rater, r);
                if (dot >= 0) {
                    movieAndDot.put(r, dot);

                }
            }
        }
        Map<Rater, Double> sorted = new LinkedHashMap<>();
        movieAndDot.entrySet().stream()
                .sorted(Map.Entry.<Rater, Double>comparingByValue().reversed())
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        return sorted;
    }

    public List<Long> getSimilarRatingsByFilter(Rater rater, Set<Rater> raterList, int numSimilarRaters,
                                                int minimalRaters, Set<Movie> movies) {
        ArrayList<Long> result = new ArrayList<>();
        Map<Rater, Double> similarDot = getSimilarities(rater, raterList);
        if (numSimilarRaters > similarDot.size()) {
            numSimilarRaters = similarDot.size();
        }
        for (Movie movie : movies) {
            int x = 0;
            Iterator<Rater> raterIterator = similarDot.keySet().iterator();
            if (numSimilarRaters > similarDot.size()) {
                numSimilarRaters = similarDot.size();
            }
            for (int i = 0; i < numSimilarRaters; i++) {
                Rater r = raterIterator.next();
                Rating tempRating = new Rating();
                tempRating.setMovieId(movie.getMovieId());
                if (r.hasRated(tempRating)) {
                    x++;
                }
            }
            if (x >= minimalRaters) {
                result.add(movie.getMovieId());
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }

    public List<Long> getBestMovies(Rater rater, Set<Rater> raterList, int size) {

        List<Long> result = new ArrayList<>();
        Map<Rater, Double> raterDoubleMap = getSimilarities(rater, raterList);
        if (raterDoubleMap == null || raterDoubleMap.size() == 0) {
            return null;
        }
        Iterator<Rater> raterIterator = raterDoubleMap.keySet().iterator();
        Rater r = raterIterator.next();
        List<Rating> ratingList = r.getRatings();
        for (int i = 0; i < size; i++) {
            result.add(ratingList.get(i).getMovieId());
        }
        return result;
    }
}
