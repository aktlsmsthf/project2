package com.example.user.cs296_project_2;

/**
 * Created by user on 2017-12-30.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current tabs
        switch (position) {
            case 0:
                FragmentA tabFragment1 = new FragmentA();
                return tabFragment1;
            case 1:
                FragmentB tabFragment2 = new FragmentB();
                return tabFragment2;
            case 2:
                FragmentC tabFragment3 = new FragmentC();
                return tabFragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}