package cz.uhk.homerecipes.dao;

import cz.uhk.homerecipes.models.Recipe;

import java.util.List;

public interface RecipeDAO {

    void createRecipe(Recipe recipe);
    void updateRecipe(Recipe recipe);
    List<Recipe> getAllRecipes();
    List<Recipe> getAllRecipes(int userId, String searchText, String sortBy, boolean desc);

    Recipe getRecipeById(int id);

    void removeRecipe(Recipe recipe);
}
