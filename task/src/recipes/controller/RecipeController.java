package recipes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.model.Recipe;
import recipes.service.RecipeService;
import recipes.service.RecipeServiceImpl;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/recipe")
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/new")
    public ResponseEntity<Map<String, Long>> addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe recipeWithId = recipeService.createRecipeWithId(recipe);
        recipeService.saveRecipe(recipeWithId);
        return new ResponseEntity<>(Map.of("id", recipeWithId.getId()), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateRecipe(@PathVariable long id,@Valid @RequestBody Recipe recipe) {
        Recipe updatedRecipe = recipeService.findRecipeById(id);
        updatedRecipe.setName(recipe.getName());
        updatedRecipe.setDescription(recipe.getDescription());
        updatedRecipe.setDescription(recipe.getDescription());
        updatedRecipe.setIngredients(recipe.getIngredients());
        updatedRecipe.setDirections(recipe.getDirections());
        updatedRecipe.setCategory(recipe.getCategory());

        LocalDateTime date;
        date = LocalDateTime.now();
        updatedRecipe.setDate(date);

        recipeService.saveRecipe(updatedRecipe);
    }

    @GetMapping("/")
    public List<Recipe> getAllRecipies(){
        return recipeService.getAllRecipies();
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        return recipeService.findRecipeById(id);
    }

    @GetMapping(value = "/search", params = "category")
    public List<Recipe> findByCategory(@RequestParam String category) {

            return recipeService.findRecipeByCategory(category);
    }

    @GetMapping(value = "/search", params = "name")

    public List<Recipe> findByName(@RequestParam String name) {
            return recipeService.findRecipeByName(name);
    }




    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipe(id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/")
    public void deleteAllRecipies() {
        recipeService.deleteAllRecipies();
    }
}