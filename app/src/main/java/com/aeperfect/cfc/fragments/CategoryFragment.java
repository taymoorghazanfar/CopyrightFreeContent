package com.aeperfect.cfc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aeperfect.cfc.R;
import com.aeperfect.cfc.interfaces.ICategoryClickListener;
import com.aeperfect.cfc.models.content.ContentCategory;
import com.aeperfect.cfc.models.content.ContentSubCategory;
import com.aeperfect.cfc.ui.adapters.RecyclerAdapterCategory;
import com.aeperfect.cfc.ui.decoration.SpacesItemDecoration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements ICategoryClickListener {

    private static final String CATEGORIES = "categories";

    private RecyclerView recyclerViewCategories;
    private RecyclerAdapterCategory adapterCategory;
    private ArrayList<ContentCategory> categories;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(ArrayList<ContentCategory> categories) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(CATEGORIES, categories);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categories = getArguments().getParcelableArrayList(CATEGORIES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        loadBannerAd(view);

        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view) {

        this.recyclerViewCategories = view.findViewById(R.id.recycler_view_category);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_item_margin);
        this.recyclerViewCategories.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        this.recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext()));

        this.adapterCategory = new RecyclerAdapterCategory(categories);
        this.adapterCategory.setCategoryClickListener(this);
        this.recyclerViewCategories.setAdapter(adapterCategory);
    }

    @Override
    public void onCategoryClick(ArrayList<ContentSubCategory> subCategories) {

        showSubCategories(subCategories);
    }

    private void showSubCategories(ArrayList<ContentSubCategory> subCategories) {

        SubCategoryFragment subCategoryFragment = SubCategoryFragment.newInstance(subCategories);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, subCategoryFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void loadBannerAd(View view) {

        LinearLayout adLayout = view.findViewById(R.id.layout_banner);
        AdView bannerAd = new AdView(getContext());
        bannerAd.setAdSize(AdSize.BANNER);
        bannerAd.setAdUnitId(getString(R.string.ad_banner_id_1));

        AdRequest adRequest = new AdRequest.Builder().build();
        bannerAd.loadAd(adRequest);

        adLayout.addView(bannerAd);
        adLayout.setGravity(Gravity.CENTER);
    }
}