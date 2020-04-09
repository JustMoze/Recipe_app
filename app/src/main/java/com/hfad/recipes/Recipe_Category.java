package com.hfad.recipes;

public class Recipe_Category {
    private int recipe_id;
    private int categry_id;

    public Recipe_Category(){}
    public Recipe_Category(int id1, int id2){
        this.recipe_id = id1;
        this.categry_id = id2;
    }
    // setters
    public void setRecipe_id(int id){
        this.recipe_id = id;
    }
    public void setCategry_id(int id){
        this.categry_id = id;
    }
    // getters
    public int getRecipe_id(){
        return this.recipe_id;
    }
    public int getCategry_id(){
        return this.categry_id;
    }
}
