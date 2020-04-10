package com.hfad.recipes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddIngredientsActivity extends AppCompatActivity {

    public static final String recipe_ID = "id";
    private int Recipe_id;
    // Ingredient names
    private EditText ing_name_1;
    private EditText ing_name_2;
    private EditText ing_name_3;
    private EditText ing_name_4;
    private EditText ing_name_5;
    private EditText ing_name_6;
    // Ingredient quantity
    private EditText ing_qnt_1;
    private EditText ing_qnt_2;
    private EditText ing_qnt_3;
    private EditText ing_qnt_4;
    private EditText ing_qnt_5;
    private EditText ing_qnt_6;

    private static List<Ingredients> ingridientList = new ArrayList<Ingredients>();
    private static List<EditText> edits_titles = new ArrayList<EditText>();
    private static List<EditText> edits_quantity = new ArrayList<EditText>();

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        ing_name_1 = findViewById(R.id.ing_name1);
        ing_name_2 = findViewById(R.id.ing_name2);
        ing_name_3 = findViewById(R.id.ing_name3);
        ing_name_4 = findViewById(R.id.ing_name4);
        ing_name_5 = findViewById(R.id.ing_name5);
        ing_name_6 = findViewById(R.id.ing_name6);

        edits_titles.add(ing_name_1);  edits_titles.add(ing_name_4);
        edits_titles.add(ing_name_2);  edits_titles.add(ing_name_5);
        edits_titles.add(ing_name_3);  edits_titles.add(ing_name_6);

        ing_qnt_1 = findViewById(R.id.quantity1);
        ing_qnt_2 = findViewById(R.id.quantity2);
        ing_qnt_3 = findViewById(R.id.quantity3);
        ing_qnt_4 = findViewById(R.id.quantity4);
        ing_qnt_5 = findViewById(R.id.quantity5);
        ing_qnt_6 = findViewById(R.id.quantity6);

        edits_quantity.add(ing_qnt_1);  edits_quantity.add(ing_qnt_4);
        edits_quantity.add(ing_qnt_2);  edits_quantity.add(ing_qnt_5);
        edits_quantity.add(ing_qnt_3);  edits_quantity.add(ing_qnt_6);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getting id
        Recipe_id = bundle.getInt(recipe_ID, 0);
        Log.d(recipe_ID, String.valueOf(Recipe_id));
    }

    public void AddIngredients(View view) {
        try {
            fillList();
            Log.d("Preventing empty list.", ingridientList.get(0).getName());
            new addIngredients().execute(Recipe_id);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (IndexOutOfBoundsException e){
            Toast.makeText(this, "Please add some ingredients to a recipe ", Toast.LENGTH_SHORT).show();
        }
    }

    public void setVisibleLayout(View view) {
        LinearLayout invisible_layout = findViewById(R.id.invisible_layout);
        invisible_layout.setVisibility(View.VISIBLE);
    }
    private static void addToList(EditText title, EditText quantity){
        if (!title.getText().toString().equals("") && !quantity.getText().toString().equals("")){
            Ingredients ingredient = new Ingredients(title.getText().toString(), quantity.getText().toString());
            ingridientList.add(ingredient);
        }
    }
    private static void fillList(){
        for (int i = 0; i < edits_titles.size(); i++){
            addToList(edits_titles.get(i), edits_quantity.get(i));
        }
    }
    private class addIngredients extends AsyncTask<Integer, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Integer... integers) {
            int recipeID = integers[0];
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(AddIngredientsActivity.this);
            try {
                db = recipeDatabaseHelper.getWritableDatabase();
                for (Ingredients ingredient : ingridientList){
                    ContentValues ingredientValues = new ContentValues();

                    ingredientValues.put("NAME", ingredient.getName());
                    ingredientValues.put("QUANTITY", ingredient.getQuantity());
                    ingredientValues.put("RECIPE_ID", recipeID);
                    db.insert("ingredient", null, ingredientValues);
                }
                return true;
            }catch (SQLiteException e){
                Toast.makeText(AddIngredientsActivity.this, "Database is unavailable! ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        protected void onPostExecute(Boolean success){
            if (!success){
                Toast toast = Toast.makeText(AddIngredientsActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (db.isOpen()){
            db.close();
        }
    }
}
