package com.djevtic.wifiloger.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.djevtic.wifiloger.fragment.WiFiListFragment;

/**
 * Created by djevtic on 13.2.17..
 */

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new WiFiListFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence name = "";
        switch (position){
            case 0:
                name = "List";
                break;
            case 1:
                name = "Map";
                break;
        }
        return name;
    }
}
