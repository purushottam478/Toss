package com.example.rajulnahar.toss.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rajulnahar.toss.Activity.MainActivity;
import com.example.rajulnahar.toss.Activity.MiniDebateActivity;
import com.example.rajulnahar.toss.R;
import com.example.rajulnahar.toss.Adapters.VerticalPagerAdapter;
import com.example.rajulnahar.toss.Containers.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MiniDebateMainContentFragment extends Fragment {


    VerticalViewPager verticalViewPager;
    VerticalPagerAdapter verticalPagerAdapter;
    public List<Fragment> fragmentList;
    public TextView minidebate;
    public TextView featureddebate;
    public LinearLayout ll_nav;
    public LinearLayout ll_featured;
    public LinearLayout ll_mini;

    public MiniDebateMainContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_mini_debate_main_content, container, false);
        verticalViewPager = (VerticalViewPager) view.findViewById(R.id.miniviewpager);
        minidebate = (TextView) view.findViewById(R.id.minidebates);
        ll_nav = (LinearLayout) view.findViewById(R.id.nav);
        featureddebate = (TextView) view.findViewById(R.id.featureddebate);
        fragmentList = new ArrayList<>();
        verticalPagerAdapter = new VerticalPagerAdapter(getChildFragmentManager());
        verticalPagerAdapter.setFragmentList(getMiniDebates());
        verticalViewPager.setAdapter(verticalPagerAdapter);

        ll_featured = (LinearLayout) view.findViewById(R.id.ll_featuredDebate);
        ll_mini = (LinearLayout) view.findViewById(R.id.ll_minidebate);
        ll_mini.setAlpha((float) 0.9);
        ll_mini.setBackgroundColor(getResources().getColor(R.color.onselect));
        ll_featured.setAlpha((float) 0.4);
        ll_featured.setBackgroundColor(getResources().getColor(R.color.notselect));

        featureddebate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MiniDebateMainContentFragment.this.getContext(),MainActivity.class);
                startActivity(intent);
                ((MiniDebateActivity)MiniDebateMainContentFragment.this.getActivity()).finish();

            }
        });


        return view;
    }

    public List<Fragment> getMiniDebates()  {
        fragmentList.clear();
        fragmentList.add(new MiniDebateMain());
        fragmentList.add(new MiniDebateMain());
        fragmentList.add(new MiniDebateMain());
        fragmentList.add(new MiniDebateMain());
        return fragmentList;

    }
    public void hidenav(){
        if(ll_nav.getY() == 0){
            ll_nav.animate().translationY(-200).withLayer();
        }else
        {
            ll_nav.animate().translationY(0).withLayer();
        }
    }

}
