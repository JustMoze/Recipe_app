package com.hfad.recipes;

public class Data {
    private static final int size = 3;
    public Recipe [] fillRecipes(){
        Recipe[] recipes_list;
        recipes_list = new Recipe[]{
                new Recipe("Watermelon Sangria", "5 minutes", R.drawable.drink1),
                new Recipe("Toasted Marshmallow Campfire Cocktail", "5-6 minutes", R.drawable.drink2),
                new Recipe("40 Easy Summer Salads for Your Next Barbecue", "30 minutes", R.drawable.salads)
        };
        return recipes_list;
    }

    public Ingredients[] fillIngredients(){
        Ingredients[] ingredients_list;
        ingredients_list = new Ingredients[]{
                new Ingredients("Apple", "5-6", 1),
                new Ingredients("Kiwi", "3.5", 2),
                new Ingredients("Milk", "1 - 1.5 liters", 3)
        };
        return ingredients_list;
    }
    public Category[] fillCategories(){
        Category[] category_list;
        category_list = new Category[]{
                new Category("Salads", 3),
                new Category("Drinks", 1),
                new Category("Drinks", 2)
        };
        return category_list;
    }
}
