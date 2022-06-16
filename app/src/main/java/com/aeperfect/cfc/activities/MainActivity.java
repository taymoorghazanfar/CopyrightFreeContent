package com.aeperfect.cfc.activities;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aeperfect.cfc.R;
import com.aeperfect.cfc.data.DataManager;
import com.aeperfect.cfc.fragments.CategoryFragment;
import com.aeperfect.cfc.interfaces.IBonusClickListener;
import com.aeperfect.cfc.interfaces.IDrawerMenuClickListener;
import com.aeperfect.cfc.interfaces.IToolClickListener;
import com.aeperfect.cfc.models.bonus.BonusFeature;
import com.aeperfect.cfc.models.editor.EditingTool;
import com.aeperfect.cfc.ui.adapters.ExpandableAdapterBonus;
import com.aeperfect.cfc.ui.adapters.ExpandableAdapterTools;
import com.aeperfect.cfc.ui.widgets.DialogAbout;
import com.aeperfect.cfc.utils.GraphicUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.List;

import static com.aeperfect.cfc.utils.ListUtil.setListViewHeight;

public class MainActivity extends AppCompatActivity implements
        IToolClickListener, IBonusClickListener,
        ExpandableListView.OnGroupClickListener,
        View.OnClickListener, IDrawerMenuClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ExpandableListView expandableListTools;
    private ExpandableListView expandableListBonus;
    private ExpandableAdapterTools adapterTools;
    private ExpandableAdapterBonus adapterBonus;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this);
        loadInterstitialAd();

        dataManager = new DataManager(this);

        GraphicUtils.initLogosMap(this);
        GraphicUtils.initSocialLogosMap(this);
        GraphicUtils.initBackgroundsMap(this);
        GraphicUtils.initToolLogosMap(this);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        initDrawer();

        initDrawerMenu();

        initExpandableListTools();

        initExpandableListBonus();

        openContentFragment();
    }

    private void initDrawer() {

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initDrawerMenu() {

        View menuDownloads = findViewById(R.id.drawer_menu_downloads);
        menuDownloads.setOnClickListener(this);

        View menuPrivacy = findViewById(R.id.drawer_menu_privacy);
        menuPrivacy.setOnClickListener(this);

        View menuRate = findViewById(R.id.drawer_menu_rate);
        menuRate.setOnClickListener(this);

        View menuShare = findViewById(R.id.drawer_menu_share);
        menuShare.setOnClickListener(this);

        View menuAbout = findViewById(R.id.drawer_menu_about);
        menuAbout.setOnClickListener(this);
    }

    private void initExpandableListTools() {

        this.expandableListTools = findViewById(R.id.expandable_list_tools);

        HashMap<String, List<EditingTool>> hashMapTools = dataManager.getEditingTools();

        adapterTools = new ExpandableAdapterTools(hashMapTools);
        adapterTools.setToolClickListener(this);

        expandableListTools.setAdapter(adapterTools);

        expandableListTools.setOnGroupClickListener(this);
    }

    private void initExpandableListBonus() {

        this.expandableListBonus = findViewById(R.id.expandable_list_bonus);

        HashMap<String, List<BonusFeature>> hashMapBonus = dataManager.getBonusContent();

        adapterBonus = new ExpandableAdapterBonus(hashMapBonus);
        adapterBonus.setBonusClickListener(this);

        expandableListBonus.setAdapter(adapterBonus);
        expandableListBonus.setOnGroupClickListener(this);

    }

    private void openContentFragment() {

        CategoryFragment contentFragment = CategoryFragment.newInstance(dataManager.getContentCategories());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, contentFragment);
        fragmentTransaction.commit();
    }

    private void gotoWebView(String title, String url) {

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        }

        Intent intent = new Intent(MainActivity.this, WebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void onEditingToolClick(EditingTool editingTool) {

        gotoWebView(editingTool.getTitle(), editingTool.getUrl());
    }

    @Override
    public void onBonusFeatureClicked(BonusFeature bonusFeature) {

        gotoWebView(bonusFeature.getTitle(), bonusFeature.getUrl());
    }

    @Override
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

        setListViewHeight(expandableListView, i);
        return false;
    }

    @Override
    public void onDownloadsClick() {

        startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
    }

    @Override
    public void onPrivacyClick() {

        gotoWebView("Privacy Policy", getString(R.string.privacy_policy));
    }

    @Override
    public void onRateClick() {

        try {
            startActivity(new Intent
                    (Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));

        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    @Override
    public void onShareClick() {

        final String appPackageName = getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Download this awesome app to get all the copyright free content:" +
                        " https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void onAboutClick() {

        DialogAbout.show(this);
    }

//    @Override
//    public void onBackPressed() {
//
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStackImmediate();
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.drawer_menu_downloads) {

            onDownloadsClick();

        } else if (view.getId() == R.id.drawer_menu_privacy) {

            onPrivacyClick();

        } else if (view.getId() == R.id.drawer_menu_rate) {

            onRateClick();

        } else if (view.getId() == R.id.drawer_menu_share) {

            onShareClick();
        } else if (view.getId() == R.id.drawer_menu_about) {

            onAboutClick();
        }
    }

    private void loadInterstitialAd() {

        final InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ad_interstitial_id_1));
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {

                interstitialAd.show();
            }
        });
    }
}