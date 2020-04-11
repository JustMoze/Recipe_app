package com.hfad.recipes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class Drinks_fragment extends Fragment {
    private List<Recipe> drinkRecipes = new ArrayList<Recipe>();
    private SQLiteDatabase db;
    private Cursor cursor;
    private RecipesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int randNumber = 7;
        new GetDrinks().execute(randNumber);
        adapter = new RecipesAdapter(drinkRecipes);
        layoutManager = new LinearLayoutManager(getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView drinksRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_recipes_fragment, container, false);
        drinksRecycler.setHasFixedSize(true);
        drinksRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);
        drinksRecycler.setLayoutManager(layoutManager);
        drinksRecycler.setAdapter(adapter);
        adapter.setListener(position -> {
            int recipe_ID = drinkRecipes.get(position)._id;
            Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
            intent.putExtra(RecipeDetailActivity.RECIPE_ID, recipe_ID);
            getActivity().startActivity(intent);
        });
        // set layout appearance
        return drinksRecycler;
    }

    private class GetDrinks extends AsyncTask<Integer, Void, Boolean>{
        private ContentValues drinkValues;
        String titles = "";
        protected void onPreExecute(){
            // code that still runs in main thread
        }
        @Override
        protected Boolean doInBackground(Integer... integers) {
            int value = integers[0];
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getActivity());
            RecipeDatabaseHelper helper = new RecipeDatabaseHelper(getActivity());
            try {
                db = recipeDatabaseHelper.getReadableDatabase();
                Log.d("Duombaze", "db was readed");
                String sql = "SELECT recipe._ID, recipe.NAME, recipe.DURATION, recipe.IMAGE FROM `recipe` INNER JOIN recipe_category ON recipe._ID = recipe_category.recipe_ID INNER JOIN category ON category._ID = recipe_category.category_ID AND category.NAME = 'Drinks'";
                cursor = db.rawQuery(sql, null);
                if (cursor == null) return null;
                else {
                    while (cursor.moveToNext()){
                        Recipe recipe = new Recipe();
                        recipe._id = cursor.getInt(0);
                        recipe.name = cursor.getString(1);
                        recipe.duration = cursor.getString(2);
                        recipe.recipe_image = cursor.getInt(3);
                        drinkRecipes.add(recipe);
                    }
                }
                return true;
            } catch (SQLiteException e){
                return false;
            }
        }
        protected void onPostExecute(Boolean success){
            if (!success){
                Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                // code after background thread was running
                for (Recipe recipe : drinkRecipes){
                    Log.d("Recipes title", recipe.getName() + "\n");
                }
            }
        }
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        cursor.close();
        db.close();
    }
}
