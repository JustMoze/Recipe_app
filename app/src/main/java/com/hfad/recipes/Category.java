package com.hfad.recipes;

public class Category {
    int _id;
    String category_name;
    int foreign_key;

    public Category(){}

    public Category(String name){
        this.category_name = name;
    }
    public Category(String name, int foreign_key){
        this.category_name = name;
        this.foreign_key = foreign_key;
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
    public int getForeign_key() {return this.foreign_key;}
}
