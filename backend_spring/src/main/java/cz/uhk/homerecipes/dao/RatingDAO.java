package cz.uhk.homerecipes.dao;

import cz.uhk.homerecipes.models.Rating;

import java.util.List;

public interface RatingDAO {
    List<Rating> getAllRatingsByRecipe(int recipeId);

    void createRating(Rating rating);
}
