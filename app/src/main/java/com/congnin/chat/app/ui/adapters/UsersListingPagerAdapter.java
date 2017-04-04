package com.congnin.chat.app.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.congnin.chat.app.ui.fragments.UsersFragment;

/**
 * Created by congnc on 4/4/17.
 */

public class UsersListingPagerAdapter extends FragmentPagerAdapter {
    private static final Fragment[] sFragments = new Fragment[]{
            UsersFragment.newInstance(UsersFragment.TYPE_ALL)};

    private static final String[] sTitles = new String[]{"All Users"};

    public UsersListingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return sFragments[position];
    }

    @Override
    public int getCount() {
        return sFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sTitles[position];
    }
}
