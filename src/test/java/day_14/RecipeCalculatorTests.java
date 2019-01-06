package day_14;

import org.junit.Assert;
import org.junit.Test;

public class RecipeCalculatorTests {

    private RecipeCalculator recipeCalulator;

    @Test
    public void iteratesRecipesOne() {
        recipeCalulator = new RecipeCalculator();
        Assert.assertEquals("5158916779", recipeCalulator.getNextTenRecipes(9));
    }

    @Test
    public void iteratesRecipesTwo() {
        recipeCalulator = new RecipeCalculator();
        Assert.assertEquals("0124515891", recipeCalulator.getNextTenRecipes(5));
    }

    @Test
    public void iteratesRecipesThree() {
        recipeCalulator = new RecipeCalculator();
        Assert.assertEquals("9251071085", recipeCalulator.getNextTenRecipes(18));
    }

    @Test
    public void iteratesRecipesFour() {
        recipeCalulator = new RecipeCalculator();
        Assert.assertEquals("5941429882", recipeCalulator.getNextTenRecipes(2018));
    }

    @Test
    public void calculatesRecipeCountOne() {
        recipeCalulator = new RecipeCalculator();
        Assert.assertEquals(9, recipeCalulator.getRecipeCountBeforeSequence("51589"));
    }


    @Test
    public void calculatesRecipeCountTwo() {
        recipeCalulator = new RecipeCalculator();
        Assert.assertEquals(5, recipeCalulator.getRecipeCountBeforeSequence("01245"));
    }


    @Test
    public void calculatesRecipeCountThree() {
        recipeCalulator = new RecipeCalculator();
        Assert.assertEquals(18, recipeCalulator.getRecipeCountBeforeSequence("92510"));
    }


    @Test
    public void calculatesRecipeCountFour() {
        recipeCalulator = new RecipeCalculator();
        Assert.assertEquals(2018, recipeCalulator.getRecipeCountBeforeSequence("59414"));
    }
}

