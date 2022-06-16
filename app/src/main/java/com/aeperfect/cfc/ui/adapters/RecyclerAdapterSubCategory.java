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
import com.aeperfect.cfc.interfaces.ISubCategoryClickListener;
import com.aeperfect.cfc.models.content.ContentSubCategory;
import com.aeperfect.cfc.utils.GraphicUtils;

import java.util.ArrayList;

public class RecyclerAdapterSubCategory extends RecyclerView.Adapter<RecyclerAdapterSubCategory.ViewHolder> {

    private final ArrayList<ContentSubCategory> subCategories;
    private ISubCategoryClickListener subCategoryClickListener;

    public RecyclerAdapterSubCategory(ArrayList<ContentSubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public void setSubCategoryClickListener(ISubCategoryClickListener subCategoryClickListener) {
        this.subCategoryClickListener = subCategoryClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_item_sub_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ContentSubCategory subCategory = subCategories.get(position);

        holder.layoutSubCategory.setBackground(GraphicUtils.getBackground(subCategory.getParentId()));

        if (subCategory.getParentId() == 1) {

            holder.imageViewLogo.setImageDrawable(GraphicUtils.getSocialLogo(subCategory.getId()));

        } else {

            holder.imageViewLogo.setImageDrawable(GraphicUtils.getLogo(subCategory.getParentId()));
        }
        holder.textViewTitle.setText(subCategory.getTitle());

    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout layoutSubCategory;
        private final ImageView imageViewLogo;
        private final TextView textViewTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutSubCategory = itemView.findViewById(R.id.layout_sub_category);
            imageViewLogo = itemView.findViewById(R.id.image_view_logo);
            textViewTitle = itemView.findViewById(R.id.text_view_title);

            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();

                if (subCategoryClickListener != null
                        && position != RecyclerView.NO_POSITION) {

                    ContentSubCategory subCategory = subCategories.get(position);

                    subCategoryClickListener.onSubCategoryClick(subCategory);
                }
            });
        }
    }
}
