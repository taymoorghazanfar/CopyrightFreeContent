package com.aeperfect.cfc.interfaces;

import com.aeperfect.cfc.models.content.ContentSubCategory;

import java.util.ArrayList;

public interface ICategoryClickListener {

    void onCategoryClick(ArrayList<ContentSubCategory> subCategories);

}
