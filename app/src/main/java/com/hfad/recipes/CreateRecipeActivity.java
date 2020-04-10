package com.hfad.recipes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeActivity extends AppCompatActivity {
    private static List<String> categoryList = new ArrayList<String>();
    private LinearLayout first_row;
    private LinearLayout second_row;
    private LinearLayout third_row;
    private static SQLiteDatabase db;

    private int image_ID;
    private EditText recipe_name_text;
    private EditText recipe_duration_text;
    private EditText recipe_instruction_text;

    private String recipeName;
    private String recipeDuration;
    private String recipeInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        first_row = findViewById(R.id.first_row);
        second_row = findViewById(R.id.second_row);
        third_row = findViewById(R.id.third_row);
        // find editText views
        recipe_name_text = findViewById(R.id.recipe_name);
        recipe_duration_text = findViewById(R.id.recipe_duration_create);
        recipe_instruction_text = findViewById(R.id.instruction_field);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickDone(View view) {
        try {
            gatherAllChecks(first_row);
            gatherAllChecks(second_row);
            gatherAllChecks(third_row);

            image_ID = chooseDefaultImage(categoryList.get(0));
            recipeName = recipe_name_text.getText().toString();
            recipeDuration = recipe_duration_text.getText().toString();
            recipeInstruction = recipe_instruction_text.getText().toString();
            if (recipeName.equals("") || recipeDuration.equals("") || recipeInstruction.equals("")){
                    throw new IllegalArgumentException("Please fill all required fields!");
            }
            // get db table row count
            long count = getRecipesCount();
            int newRecipeID = (int) count + 1;
            new addRecipe().execute(newRecipeID);
            Bundle bundle = new Bundle();
            Intent intent = new Intent(this, AddIngredientsActivity.class);
            bundle.putInt(AddIngredientsActivity.recipe_ID, newRecipeID);
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (IndexOutOfBoundsException e){
            Toast.makeText(this, "Please check on of the checkbox options!", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private static void gatherAllChecks(LinearLayout layout){
        for (int i = 0; i < layout.getChildCount(); i++){
            View v = layout.getChildAt(i);
            if (v instanceof CheckBox){
                if (((CheckBox)v).isChecked()){
                    categoryList.add(((CheckBox) v).getText().toString());
                }
            }
        }
    }
    private static int chooseDefaultImage(String category_name){
        switch (category_name){
            case "Breakfast":
                return R.drawable.breakfast_def;
            case "Lunch":
                return R.drawable.lunch_def;
            case "Dinner":
                return R.drawable.dinner_def;
            case "Salads":
                return R.drawable.salads_def;
            case "Dessert":
                return R.drawable.dessert_def;
            case "Drinks":
                return R.drawable.drinks_def;
            case "Soup":
                return R.drawable.soup_def;
            default:
                return 0;
        }
    }
    public long getRecipesCount(){
        RecipeDatabaseHelper helper = new RecipeDatabaseHelper(this);
        db = helper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "recipe");
        db.close();
        return count;
    }
    private class addRecipe extends AsyncTask<Integer, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Integer... integers) {
            int recipeID = integers[0];
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(CreateRecipeActivity.this);
            RecipeDatabaseHelper helper = new RecipeDatabaseHelper(CreateRecipeActivity.this);
            try {
                db = recipeDatabaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("NAME", recipeName);
                contentValues.put("DURATION", recipeDuration);
                contentValues.put("IMAGE", image_ID);
                contentValues.put("INSTRUCTION", recipeInstruction);

                db.insert("recipe", null, contentValues);

                // add relationships with category
                for (String category : categoryList){
                    ContentValues junctionValues = new ContentValues();
                    junctionValues.put("recipe_ID", recipeID);
                    switch (category){
                        case "Breakfast":
                            junctionValues.put("category_ID", 6);
                            break;
                        case "Lunch":
                            junctionValues.put("category_ID", 3);
                            break;
                        case "Dinner":
                            junctionValues.put("category_ID", 4);
                            break;
                        case "Salads":
                            junctionValues.put("category_ID", 1);
                            break;
                        case "Drinks":
                            junctionValues.put("category_ID", 2);
                            break;
                        case "Dessert":
                            junctionValues.put("category_ID", 7);
                            break;
                        case "Soup":
                            junctionValues.put("category_ID", 5);
                            break;
                    }
                    db.insert("recipe_category", null, junctionValues);
                }
                db.close();
                return true;

            } catch (SQLiteException e){
                return false;
            }
        }
        protected void onPostExecute(Boolean success){
            if (!success){
                Toast toast = Toast.makeText(CreateRecipeActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        categoryList.clear();
    }
}
