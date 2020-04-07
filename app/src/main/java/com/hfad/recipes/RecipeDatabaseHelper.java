package com.hfad.recipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecipeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recipes";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_1 = "recipe";
    private static final String TABLE_2 = "ingredient";
    private static final String TABLE_3 = "category";
    private static SQLiteDatabase db;
    // Capitalize class name
    Data data_to_fill = new Data();
    RecipeDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateRecipesDatabase(db, oldVersion, newVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        updateRecipesDatabase(db,  0, DATABASE_VERSION);


    }
    private void updateRecipesDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion == 1){
            db.execSQL("CREATE TABLE " + TABLE_1 + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT,"
                    + "DURATION TEXT,"
                    + "IMAGE INTEGER);");
            db.execSQL("CREATE TABLE "+ TABLE_2 + "(_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "QUANTITY TEXT, "
                    + "RECIPE_ID INTEGER,"
                    + "FOREIGN KEY(RECIPE_ID) REFERENCES "+ TABLE_1 +"(_ID));");
            db.execSQL("CREATE TABLE " + TABLE_3 + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "RECIPE_ID INTEGER,"
                    + "FOREIGN KEY(RECIPE_ID) REFERENCES " + TABLE_1 +"(_ID));");
            // Insert data into tables
            Recipe[] recipesToInsert = data_to_fill.fillRecipes();
            insertRecipes(db, recipesToInsert);

            Ingredients[] ingredientsToInsert = data_to_fill.fillIngredients();
            insertIngredients(db, ingredientsToInsert);

            Category[] categoriesToInsert = data_to_fill.fillCategories();
            insertCategories(db, categoriesToInsert);

        } else {
            // some other code
        }
    }
    // Insert one object to table
    private void insertRecipe(SQLiteDatabase db, Recipe recipe){
        ContentValues recipeValues = new ContentValues();
        recipeValues.put("NAME", recipe.getName());
        recipeValues.put("DURATION", recipe.getDuration());
        recipeValues.put("IMAGE", recipe.getRecipe_image());

        db.insert(TABLE_1, null, recipeValues);
    }
    private void insertIngredient(SQLiteDatabase db, Ingredients ingredient){
        ContentValues ingredientValues = new ContentValues();
        ingredientValues.put("NAME", ingredient.getName());
        ingredientValues.put("QUANTITY", ingredient.getQuantity());
        ingredientValues.put("RECIPE_ID", ingredient.getForeign_key());

        db.insert(TABLE_2, null, ingredientValues);
    }
    private void insertCategory(SQLiteDatabase db, Category category){
        ContentValues categoryValues = new ContentValues();
        categoryValues.put("NAME", category.getCategory_name());
        categoryValues.put("RECIPE_ID", category.getForeign_key());

        db.insert(TABLE_3, null, categoryValues);
    }
    // inserting multiple data
    private void insertRecipes(SQLiteDatabase db, Recipe[] recipes){
        for(Recipe recipe : recipes){
            insertRecipe(db, recipe);
        }
    }
    private void insertIngredients(SQLiteDatabase db, Ingredients[] ingredients){
        for (Ingredients ingredient : ingredients){
            insertIngredient(db, ingredient);
        }
    }
    private void insertCategories(SQLiteDatabase db, Category[] categories){
        for (Category category : categories){
            insertCategory(db, category);
        }
    }
}
