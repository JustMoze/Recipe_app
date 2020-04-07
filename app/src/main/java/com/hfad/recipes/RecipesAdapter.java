package com.hfad.recipes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    private List<Recipe> recipeList;
    private int size;

    private Listener listener;

    interface Listener {
        void onClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public RecipesAdapter(List<Recipe> recipeList){
        this.recipeList = recipeList;
    }
    @Override
    public int getItemCount() {
        return recipeList.size();
    }
    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent,false);
        return new ViewHolder(v);
    }

    @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.card_img);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), recipeList.get(position).recipe_image);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(recipeList.get(position).name);
        TextView titleView = cardView.findViewById(R.id.card_title);
        titleView.setText(recipeList.get(position).name);

        TextView durationView = cardView.findViewById(R.id.card_duration);
        durationView.setText(recipeList.get(position).duration);

        cardView.setOnClickListener(v -> {
            if (listener != null){
                listener.onClick(position);
            }
        });
    }
}
