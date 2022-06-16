package com.aeperfect.cfc.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aeperfect.cfc.R;
import com.aeperfect.cfc.activities.WebActivity;
import com.aeperfect.cfc.interfaces.ISubCategoryClickListener;
import com.aeperfect.cfc.models.content.ContentSubCategory;
import com.aeperfect.cfc.ui.adapters.RecyclerAdapterSubCategory;
import com.aeperfect.cfc.ui.decoration.GridItemDecoration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

import java.util.ArrayList;

public class SubCategoryFragment extends Fragment implements ISubCategoryClickListener {

    private static final String SUB_CATEGORIES = "sub_categories";

    private RecyclerView recyclerViewSubCategories;
    private RecyclerAdapterSubCategory adapterSubCategory;
    private ArrayList<ContentSubCategory> subCategories;

    public SubCategoryFragment() {
        // Required empty public constructor
    }

    public static SubCategoryFragment newInstance(ArrayList<ContentSubCategory> subCategories) {
        SubCategoryFragment fragment = new SubCategoryFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(SUB_CATEGORIES, subCategories);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subCategories = getArguments().getParcelableArrayList(SUB_CATEGORIES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sub_category, container, false);

        loadBannerAd(view);

        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view) {

        this.recyclerViewSubCategories = view.findViewById(R.id.recycler_view_sub_category);
        this.recyclerViewSubCategories.setLayoutManager(
                new GridLayoutManager(getContext(),
                        2, RecyclerView.VERTICAL, false));

        this.recyclerViewSubCategories.addItemDecoration(new GridItemDecoration(getContext(), R.dimen.sub_cat_margin));

        this.adapterSubCategory = new RecyclerAdapterSubCategory(subCategories);
        this.adapterSubCategory.setSubCategoryClickListener(this);
        this.recyclerViewSubCategories.setAdapter(adapterSubCategory);
    }


    @Override
    public void onSubCategoryClick(ContentSubCategory subCategory) {

        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra("title", subCategory.getTitle());
        intent.putExtra("url", subCategory.getUrl());
        startActivity(intent);
    }

    private void loadBannerAd(View view) {

        LinearLayout adLayout = view.findViewById(R.id.layout_banner);
        AdView bannerAd = new AdView(getContext());
        bannerAd.setAdSize(AdSize.BANNER);
        bannerAd.setAdUnitId(getString(R.string.ad_banner_id_2));

        AdRequest adRequest = new AdRequest.Builder().build();
        bannerAd.loadAd(adRequest);

        adLayout.addView(bannerAd);
        adLayout.setGravity(Gravity.CENTER);
    }
}