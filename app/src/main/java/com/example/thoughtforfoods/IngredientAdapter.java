package com.example.thoughtforfoods;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thoughtforfoods.data.Database;
import com.example.thoughtforfoods.data.IngredientData;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    List<IngredientData> ingredients;


    public IngredientAdapter(List<IngredientData> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IngredientData ingredient = ingredients.get(position);
        holder.tvIngredientName.setText(ingredient.getName());
        holder.btnDelete.setOnClickListener(v -> {
            // Remove from database
            Database.getInstance(holder.itemView.getContext()).removeIngredientFromPantry(ingredient.getName());

            // Notify adapter
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, ingredients.size());
        });

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIngredientName;
        ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvIngredientName = itemView.findViewById(R.id.tvIngredientName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}