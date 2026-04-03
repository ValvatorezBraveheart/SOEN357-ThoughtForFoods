package com.example.thoughtforfoods;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thoughtforfoods.data.PantrySectionData;

import java.util.List;
public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.ViewHolder> {

    private final List<PantrySectionData> sections;

    public PantryAdapter(List<PantrySectionData> sections) {
        this.sections = sections;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_section, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PantrySectionData section = sections.get(position);

        // Display category title
        holder.title.setText(capitalize(section.getCategory()));

        // Setup inner RecyclerView
        holder.innerRecycler.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.innerRecycler.setAdapter(new IngredientAdapter(section.getIngredientInPantry()));

        // Disable nested scrolling
        holder.innerRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final RecyclerView innerRecycler;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.typeOfIngredient);
            innerRecycler = itemView.findViewById(R.id.ingredientRecyclerView);
        }
    }

    // Format first letter capitalize
    private String capitalize(Enum<?> category) {
        String name = category.name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}