package com.hfad.recipes;

public class Category {
    private int _id;
    private String category_name;

    public Category(){}

    public Category(String name){
        this.category_name = name;
    }

    public Category(int id, String name){
        this._id = id;
        this.category_name = name;
    }
    // setters
    public void setCategory_name(String name){
        this.category_name = name;
    }
    // getters
    public String getCategory_name(){
        return this.category_name;
    }
    public int get_id(){
        return this._id;
    }
}
