package com.example.shivamvk.moviesimdb;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FavoritesActivity extends AppCompatActivity {

    ViewPager vpFavoritesActivity;
    TabLayout tabLayoutFavoritesActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        vpFavoritesActivity = findViewById(R.id.view_pager_favorites_activity);
        FavoritesViewPagerAdapter favoritesViewPagerAdapter = new FavoritesViewPagerAdapter(this, getSupportFragmentManager());
        vpFavoritesActivity.setAdapter(favoritesViewPagerAdapter);

        tabLayoutFavoritesActivity = findViewById(R.id.tabs_favorites_activity);
        tabLayoutFavoritesActivity.setupWithViewPager(vpFavoritesActivity);

    }
}
