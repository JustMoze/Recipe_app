package com.hfad.recipes;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchedRecipeFragment extends Fragment {
    public static final String query = "query";
    private List<Recipe> allRecipes;
    private String searchQuery;
    private SQLiteDatabase db;
    private RecipesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Cursor cursor;
    public SearchedRecipeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int randNumber = 7;
        try {
            this.searchQuery = getArguments().getString(query);
        } catch (NullPointerException e){
            Toast toast = Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
        allRecipes = new ArrayList<>();
        new getSearchedRecipes().execute(randNumber);
        adapter = new RecipesAdapter(allRecipes);
        layoutManager = new LinearLayoutManager(getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView searchedRecipesRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_recipes_fragment, container, false);
        searchedRecipesRecycler.setHasFixedSize(true);
        searchedRecipesRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);

        searchedRecipesRecycler.setLayoutManager(layoutManager);
        searchedRecipesRecycler.setAdapter(adapter);

        adapter.setListener(position -> {
            int recipe_ID = allRecipes.get(position)._id;
            Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
            intent.putExtra(RecipeDetailActivity.RECIPE_ID, recipe_ID);
            getActivity().startActivity(intent);
        });
        return searchedRecipesRecycler;
    }
    private class getSearchedRecipes extends AsyncTask<Integer, Void, Boolean>{
        @Override
        protected Boolean doInBackground(Integer... integers) {
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getActivity());
            RecipeDatabaseHelper recipeDatabaseHelper1 = new RecipeDatabaseHelper(getActivity());
            try {
                db = recipeDatabaseHelper1.getReadableDatabase();
                Log.d("Query", query);
                cursor = recipeDatabaseHelper1.getRecipesMatches(db, searchQuery);
                while(cursor.moveToNext()){
                    Recipe recipe = new Recipe();
                    recipe._id = cursor.getInt(0);
                    recipe.name = cursor.getString(1);
                    recipe.duration = cursor.getString(2);
                    recipe.recipe_image = cursor.getInt(3);
                    allRecipes.add(recipe);
                }
                cursor.close();
                db.close();
                return true;
            } catch (SQLiteException e){
                Log.d("e message", e.getMessage());
                return false;
            }
        }
        protected void onPostExecute(Boolean success){
            if (!success){
                Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
