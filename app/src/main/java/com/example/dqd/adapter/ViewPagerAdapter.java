package com.example.dqd.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;


import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] title = new String[]{"头条","D站","热门"};
    private ArrayList<Fragment> fragments;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    public void setViewArrayList(ArrayList<Fragment> mViewArrayList){
        this.fragments =mViewArrayList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return fragments.get(1);
        } else if (position == 2) {
            return fragments.get(2);
        } else {
            return fragments.get(0);
        }
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {//设置页面标题
        return title[position];
    }

}
