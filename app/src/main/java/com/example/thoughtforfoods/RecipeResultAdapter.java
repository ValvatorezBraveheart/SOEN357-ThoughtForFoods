package com.example.thoughtforfoods;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thoughtforfoods.data.RecipeResult;

import java.util.List;

public class RecipeResultAdapter extends RecyclerView.Adapter<RecipeResultAdapter.RecipeResultViewHolder> {

    private List<RecipeResult> recipes;
    private OnRecipeClickListener listener;

    public RecipeResultAdapter(List<RecipeResult> recipes, OnRecipeClickListener listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new RecipeResultViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeResultViewHolder holder, int position) {
        RecipeResult recipe = recipes.get(position);
        holder.titleText.setText(recipe.getRecipe().getRecipeName());
        holder.prepTime.setText(recipe.getRecipe().getDetails().getTotalTime());
        int missingIngredientCount = recipe.getMissingIngredientCount();
        if (missingIngredientCount == 0){
            holder.tvHaveAllIngredient.setVisibility(VISIBLE);
            holder.ingredientsOwned.setVisibility(GONE);
        } else {
            holder.tvHaveAllIngredient.setVisibility(GONE);
            holder.ingredientsOwned.setVisibility(VISIBLE);
            holder.ingredientsOwned.setText("Missing "+recipe.getMissingIngredientCount()+" ingredients");
        }



        Glide.with(holder.itemView.getContext())
                .load(recipe.getRecipe().getRecipeImgUrl())
                .into(holder.foodImage);

        holder.itemView.setOnClickListener(view -> listener.onRecipeClick(recipe));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<RecipeResult> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public static class RecipeResultViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, prepTime, ingredientsOwned, tvHaveAllIngredient;
        ImageView foodImage;

        public RecipeResultViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            prepTime = itemView.findViewById(R.id.prepTime);
            ingredientsOwned = itemView.findViewById(R.id.ingredientsOwned);
            foodImage = itemView.findViewById(R.id.foodImage);
            tvHaveAllIngredient = itemView.findViewById(R.id.tvHaveAllIngredient);
        }
    }

    public interface OnRecipeClickListener {
        void onRecipeClick(RecipeResult recipe);
    }
}