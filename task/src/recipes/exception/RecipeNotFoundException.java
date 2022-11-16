package recipes.exception;

import static recipes.util.StringPool.RECIPE_NOT_FOUND;

public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException(long id) {
        super(String.format(RECIPE_NOT_FOUND, id));
    }
}