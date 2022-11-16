package recipes.service;

import org.hibernate.annotations.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.exception.RecipeNotFoundException;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAllRecipies() {
        return (List<Recipe>) recipeRepository.findAll();
    }
    @Override
    public List<Recipe> findRecipeByCategory(String category) {

        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    @Override
    public List<Recipe> findRecipeByName(String name) {

        return recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    @Override
    public Recipe findRecipeById(long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
    }

    @Override
    public Recipe createRecipeWithId(Recipe recipe) {
        return new Recipe
                (recipe.getName(), recipe.getCategory(), recipe.getDescription(), recipe.getIngredients(), recipe.getDirections());
    }



    @Override
    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(long id) {
        if (!recipeRepository.findById(id).isPresent()){
            throw new RecipeNotFoundException(id);
        }
        recipeRepository.deleteById(id);
    }

    @Override
    public void deleteAllRecipies() {
        recipeRepository.deleteAll();
    }
}