package com.example.rajulnahar.toss.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by Rajul Nahar on 01-01-2017.
 */

public class HorizontalPagerAdapter extends FragmentStatePagerAdapter{

    public List<Fragment>fragmentList;
    public Context thisConext;
    public Fragment currenFragment;

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public HorizontalPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setCurrenFragment(Fragment currenFragment) {
        this.currenFragment = currenFragment;
    }

    public Fragment getCurrenFragment() {
        return currenFragment;
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
