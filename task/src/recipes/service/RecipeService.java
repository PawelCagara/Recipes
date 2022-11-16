package recipes.service;

import recipes.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipies();

    Recipe findRecipeById(long id);

    List<Recipe> findRecipeByCategory(String category);

    List<Recipe> findRecipeByName(String name);

    Recipe createRecipeWithId (Recipe recipe);
    void saveRecipe(Recipe recipe);

    void deleteRecipe(long id);

    void deleteAllRecipies();
}