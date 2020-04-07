package com.hfad.recipes;

public class Recipe {
    int _id;
    String name;
    String duration;
    int recipe_image;

    public Recipe(){}

    Recipe(String name, String duration, int photo){
        this.name = name;
        this.duration = duration;
        this.recipe_image = photo;
    }
    Recipe(int id, String name, String duration, int photo){
        this._id = id;
        this.name = name;
        this.duration = duration;
        this.recipe_image = photo;
    }
    // setters
    public void setName(String name){
        this.name = name;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }
    public void setRecipe_image(int image){
        this.recipe_image = image;
    }
    // getters
    public String getName(){
        return this.name;
    }
    public String getDuration(){
        return this.duration;
    }
    public int getRecipe_image(){
        return this.recipe_image;
    }
}
