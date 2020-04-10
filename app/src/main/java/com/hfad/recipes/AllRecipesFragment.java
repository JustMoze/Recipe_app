package com.hfad.recipes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllRecipesFragment extends Fragment {

    private List<Recipe> allRecipes = new ArrayList<Recipe>();
    private SQLiteDatabase db;
    private RecipesAdapter adapter;
    private GridLayoutManager layoutManager;
    private Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int randNumber = 7;
        new getAllRecipes().execute(randNumber);
        adapter = new RecipesAdapter(allRecipes);
        layoutManager = new GridLayoutManager(getActivity(), 2);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView drinksRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_recipes_fragment, container, false);
        drinksRecycler.setHasFixedSize(true);
        drinksRecycler.setItemViewCacheSize(20);
        drinksRecycler.setDrawingCacheEnabled(true);
        drinksRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        drinksRecycler.setLayoutManager(layoutManager);
        drinksRecycler.setAdapter(adapter);
        //drinksRecycler.notifyAll();
        // set layout appearance
        adapter.setListener(position -> {
            int recipe_ID = allRecipes.get(position)._id;
            Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
            intent.putExtra(RecipeDetailActivity.RECIPE_ID, recipe_ID);
            getActivity().startActivity(intent);
        });
        return drinksRecycler;
    }

    private class getAllRecipes extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues drinkValues;
        String titles = "";
        protected void onPreExecute(){
            // code that still runs in main thread
        }
        @Override
        protected Boolean doInBackground(Integer... integers) {
            int value = integers[0];
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getActivity());
            try {
                db = recipeDatabaseHelper.getReadableDatabase();
                Log.d("Duombaze", "db was readed");
                String sql = "SELECT recipe._ID, recipe.NAME, recipe.DURATION, recipe.IMAGE FROM `recipe`";
                cursor = db.rawQuery(sql, null);
                if (cursor == null) return null;
                else {
                    while (cursor.moveToNext()){
                        Recipe recipe = new Recipe();
                        recipe._id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        if (name.length() > 17){
                            recipe.name = name.substring(0, 13) + "...";
                        } else {
                            recipe.name = name;
                        }
                        String duration = cursor.getString(2);
                        if (duration.length() > 14){
                            recipe.duration = duration.substring(0, 12) + "...";
                        } else {
                            recipe.duration = duration;
                        }
                        recipe.recipe_image = cursor.getInt(3);
                        allRecipes.add(recipe);
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
                for (Recipe recipe : allRecipes){
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
