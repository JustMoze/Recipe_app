package com.hfad.recipes;

public class Ingredients {
    private int _id;
    private String name;
    private String quantity;
    private int foreign_key;

    public Ingredients(){}
    public Ingredients(String name, String quantity){
        this.name = name;
        this.quantity = quantity;
    }
    public Ingredients(int _id, String name, String quantity){
        this.name = name;
        this.quantity = quantity;
        this._id = _id;
    }
    public Ingredients(String name, String quantity, int foreign_key){
        this.name = name;
        this.quantity = quantity;
        this.foreign_key = foreign_key;
    }
    // setters
    public void setName(String name){
        this.name = name;
    }
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }
    // getters
    public String getName(){
        return this.name;
    }
    public String getQuantity(){
        return this.quantity;
    }
    public int getForeign_key(){ return this.foreign_key;}

}
