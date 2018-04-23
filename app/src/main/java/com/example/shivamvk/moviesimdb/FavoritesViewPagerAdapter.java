package com.example.shivamvk.moviesimdb;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FavoritesViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public FavoritesViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new MoviesFavoritesFragment();
        } else {
            return new TVShowsFavoritesFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return context.getString(R.string.movies);
        } else {
            return context.getString(R.string.tvshows);
        }
    }
}
