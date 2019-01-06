package day_14;

public class RecipeCalculator {

    final StringBuffer recipes = new StringBuffer();
    int elfOne = 0;
    int elfTwo = 1;

    public RecipeCalculator() {
        recipes.append("37");
    }

    public String iterateRecipes(final int iterations) {
        for (int i = 0; i < iterations; i++) {
            generateNextRecipe();
        }
        return recipes.toString();
    }

    public String getNextTenRecipes(final int iterations) {
        return iterateRecipes(iterations + 10).substring(iterations, iterations + 10);
    }

    public int getRecipeCountBeforeSequence(final String sequence) {
        while (iterateRecipes(1000000).indexOf(sequence) == -1) {
            // Keep iterating in batches of 1 million.
        }
        return recipes.toString().indexOf(sequence);
    }



    private void generateNextRecipe() {
        final int newRecipe = Character.getNumericValue(recipes.charAt(elfOne)) + Character.getNumericValue(recipes.charAt(elfTwo));
        if (newRecipe > 9) {
            recipes.append("1");
            recipes.append(newRecipe % 10);
        } else {
            recipes.append(newRecipe);
        }

        elfOne = getNewPosition(elfOne, Character.getNumericValue(recipes.charAt(elfOne)));
        elfTwo = getNewPosition(elfTwo, Character.getNumericValue(recipes.charAt(elfTwo)));
    }

    private int getNewPosition(final int currentPosition, final int currentScore) {
        if ((currentPosition + currentScore + 1) >= recipes.length()) {
            int offset = (currentPosition + currentScore + 1) % recipes.length();
            return offset;
        } else {
            return currentPosition + currentScore + 1;
        }
    }

    public static void main(String[] args) {
        final RecipeCalculator partOne = new RecipeCalculator();
        System.out.println(partOne.getNextTenRecipes(360781));
        final RecipeCalculator partTwo = new RecipeCalculator();
        System.out.println(partTwo.getRecipeCountBeforeSequence("360781"));
    }
}
