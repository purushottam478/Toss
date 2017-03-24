package com.example.rajulnahar.toss.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.rajulnahar.toss.Fragments.MainFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajul Nahar on 01-01-2017.
 */

public class VerticalPagerAdapter extends FragmentStatePagerAdapter {

    public List<Fragment>fragmentList;


    public VerticalPagerAdapter(FragmentManager fm) {

        super(fm);
        fragmentList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;

    }

    public void setStartPage(int position)  {

        if(fragmentList.get(position) instanceof MainFragment)
        {
            ((MainFragment)fragmentList.get(position)).setStartPage();
        }
        Log.e("positon",String.valueOf(position));

    }
}
