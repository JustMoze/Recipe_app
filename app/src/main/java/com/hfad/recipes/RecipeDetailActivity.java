package com.hfad.recipes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {
    private static Recipe recipe = new Recipe();
    private List<Ingredients> allIngredients = new ArrayList<Ingredients>();
    private List<String> text_ingredients = new ArrayList<String>();
    private static int imageID;
    private Cursor cursor;
    private Cursor ingredientCursor;
    private SQLiteDatabase db;
    private static final String TABLE_1 = "recipe";
    public static final String RECIPE_ID = "recipeID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int recipe_ID = (Integer)getIntent().getExtras().get(RECIPE_ID);
        new getRecipeByID().execute(recipe_ID);
        // createIngredientList(allIngredients);
        setContentView(R.layout.activity_recipe_detail);
        ImageView recipeImage = findViewById(R.id.recipe_image);
        TextView titleView = findViewById(R.id.recipe_title);
        TextView durationView = findViewById(R.id.recipe_duration);
        // let's get an image of recipe
        recipeImage.setImageDrawable(ContextCompat.getDrawable(this, imageID));
        recipeImage.setContentDescription(recipe.getName());
        titleView.setText(recipe.getName());
        String time = "⏰ " + recipe.getDuration();
        durationView.setText(time);

        TextView ingredientName = findViewById(R.id.recipes_ingredients_list);
        TextView ingredientQuantity = findViewById(R.id.ingredients_quantity);
        String a = text_ingredients.get(0);
        String b = text_ingredients.get(1);
        ingredientName.setText(text_ingredients.get(0));
        ingredientQuantity.setText(text_ingredients.get(1));

        TextView instructionView = findViewById(R.id.instructions);
        instructionView.setText(recipe.getInstruction());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    private class getRecipeByID extends AsyncTask<Integer, Void, Boolean>{
        @Override
        protected Boolean doInBackground(@NotNull Integer... integers) {
            int id = integers[0];
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(RecipeDetailActivity.this);
            try {
                db = recipeDatabaseHelper.getReadableDatabase();
                cursor = db.query(TABLE_1,
                        new String[]{"NAME", "DURATION", "IMAGE", "INSTRUCTION"},
                        "_ID = ?",
                        new String[] {Integer.toString(id)},
                        null, null, null);
                if (cursor.moveToFirst()){
                    recipe = new Recipe(cursor.getString(0), cursor.getString(1),
                            cursor.getInt(2), cursor.getString(3));
                    imageID = cursor.getInt(2);
                }
                String sqlCode = "SELECT ingredient.NAME, ingredient.QUANTITY  FROM `ingredient` INNER JOIN recipe ON ingredient.RECIPE_ID = recipe._ID WHERE recipe._ID = " + id;
                ingredientCursor = db.rawQuery(sqlCode, null);
                String titleName = "";
                String quantityText = "";
                while(ingredientCursor.moveToNext()){
                    titleName += "🛒 " + ingredientCursor.getString(0) + " \n";
                    quantityText +=  ingredientCursor.getString(1) + " \n";
                }
                text_ingredients.add(titleName);
                text_ingredients.add(quantityText);
                return true;
            } catch (SQLiteException e){
                return false;
            }
        }
        protected void onPostExecute(Boolean success){
            if (!success){
                Toast toast = Toast.makeText(RecipeDetailActivity.this, "Database is unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
