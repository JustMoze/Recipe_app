package com.hfad.recipes;

public class Recipe {
    int _id;
    String name;
    String duration;
    int recipe_image;
    String instruction;

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
    Recipe(String name, String duration, int photo, String instruction){
        this.name = name;
        this.duration = duration;
        this.recipe_image = photo;
        this.instruction = instruction;
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
    public void setInstruction(String instruction){
        this.instruction = instruction;
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
    public String getInstruction(){
        return this.instruction;
    }
    public String getId(){
        return String.valueOf(this._id);
    }
}
