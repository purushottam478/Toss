package com.example.rajulnahar.toss.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rajulnahar.toss.Activity.MainActivity;
import com.example.rajulnahar.toss.Constants;
import com.example.rajulnahar.toss.Adapters.HorizontalPagerAdapter;
import com.example.rajulnahar.toss.Containers.HorizontalViewPager;
import com.example.rajulnahar.toss.Interfaces.OnTossInterface;
import com.example.rajulnahar.toss.R;
import com.example.rajulnahar.toss.Interfaces.ScrollingInterface;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements OnTossInterface {

    HorizontalPagerAdapter horizontalPagerAdapter;
    HorizontalViewPager horizontalViewPager;
    public List<Fragment>fragmentList;
    ScrollingInterface scrollingInterface;
    public int pagePosition;
    public int scrollState;
    public  int x;
    public int[] checkStat;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        checkStat = new int[]{0,0,0};
        Bundle bundle = getArguments();
        x = bundle.getInt("fragmentno");
        horizontalViewPager = (HorizontalViewPager) view.findViewById(R.id.horizontalviewpager);
        horizontalPagerAdapter = new HorizontalPagerAdapter(getChildFragmentManager());
        horizontalPagerAdapter.setFragmentList(getFragmentList());
        horizontalViewPager.setAdapter(horizontalPagerAdapter);
        horizontalViewPager.setCurrentItem(2);
        scrollingInterface = (MainActivity)getActivity();

        ///Log.e("mainfragpos",String.valueOf(x));
        horizontalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagePosition = position;
                if(position!=2){
                    ((MainActivity)MainFragment.this.getActivity()).ll_nav.animate().translationY(-200).withLayer();
                }
                scrollingInterface.scroll(position);
                if(position<4 &&position>0)
                {
                    checkStat[position-1] = 1;
                    //Log.e("rajul",String.valueOf(x)+"    "+String.valueOf(position));
                    if (position == 2&& checkStat[0]==1 && checkStat[1] == 1 && checkStat[2] == 1)  {
                        Constants.checkstat[x] =1;

                        ((MainContentFragment)horizontalPagerAdapter.instantiateItem(horizontalViewPager,position)).updateUI();
                    }
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                scrollState = state;
                handleState(pagePosition,scrollState);

            }
        });

        return view;
    }

    public MainContentFragment getMainContentFragment() {
        MainContentFragment mainContentFragment = new MainContentFragment();
        Bundle bundles = new Bundle();
        bundles.putInt("fragmentno",x);
        mainContentFragment.setArguments(bundles);
        return mainContentFragment;
    }

    public HeadFragment getHeadFragment()   {
        HeadFragment headFragment = new HeadFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("fragmentno",x);
        headFragment.setArguments(bundle);
        return headFragment;
    }
    public TailFragment getTailFragment()   {
        TailFragment tailFragment = new TailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("fragmentno",x);
        tailFragment.setArguments(bundle);
        return tailFragment;
    }

    public List<Fragment> getFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(getTailFragment());
        fragmentList.add(getHeadFragment());
        fragmentList.add(getMainContentFragment());
        fragmentList.add(getTailFragment());
        fragmentList.add(getHeadFragment());
        return fragmentList;


    }

    public void handleState(int position,int state) {
        if(position == 4 && state == ViewPager.SCROLL_STATE_IDLE)   {
            horizontalViewPager.setCurrentItem(1,false);
        }
        if(position == 0 && state == ViewPager.SCROLL_STATE_IDLE)   {
            horizontalViewPager.setCurrentItem(3,false);
        }
    }

    public void setStartPage()  {
        horizontalViewPager.setCurrentItem(2);
    }


    @Override
    public void toss(String tag) {
        if(tag.equals("tail")){
            horizontalViewPager.setCurrentItem(1);
        }
        else if(tag.equals("head")) {
            horizontalViewPager.setCurrentItem(3);
        }
        else if(tag.equals("main")){
            horizontalViewPager.setCurrentItem(2);
        }
    }

    @Override
    public void hide(String val) {

        scrollingInterface.hidenav("yes");
    }
}
