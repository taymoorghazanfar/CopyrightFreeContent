package com.aeperfect.cfc.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aeperfect.cfc.R;
import com.aeperfect.cfc.interfaces.ICategoryClickListener;
import com.aeperfect.cfc.models.content.ContentCategory;
import com.aeperfect.cfc.utils.GraphicUtils;

import java.util.ArrayList;

public class RecyclerAdapterCategory extends RecyclerView.Adapter<RecyclerAdapterCategory.ViewHolder> {

    private final ArrayList<ContentCategory> categories;
    private ICategoryClickListener categoryClickListener;

    public RecyclerAdapterCategory(ArrayList<ContentCategory> categories) {
        this.categories = categories;
    }

    public void setCategoryClickListener(ICategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ContentCategory category = categories.get(position);

        holder.layoutCategory.setBackground(GraphicUtils.getBackground(category.getId()));
        holder.imageViewLogo.setImageDrawable(GraphicUtils.getLogo(category.getId()));
        holder.textViewTitle.setText(category.getTitle());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout layoutCategory;
        private final ImageView imageViewLogo;
        private final TextView textViewTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutCategory = itemView.findViewById(R.id.layout_category);
            imageViewLogo = itemView.findViewById(R.id.image_view_logo);
            textViewTitle = itemView.findViewById(R.id.text_view_title);

            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();

                if (categoryClickListener != null
                        && position != RecyclerView.NO_POSITION) {

                    ContentCategory category = categories.get(position);

                    categoryClickListener.onCategoryClick(category.getSubCategories());
                }
            });
        }
    }
}
