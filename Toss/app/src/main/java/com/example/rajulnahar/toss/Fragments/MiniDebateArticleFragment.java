package com.example.rajulnahar.toss.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rajulnahar.toss.Activity.MiniDebateActivity;
import com.example.rajulnahar.toss.R;


public class MiniDebateArticleFragment extends Fragment {
    public RelativeLayout relativeLayout;
    public FloatingActionButton fab_tossminidebate;
    public TextView tv_headText;
    public float footerpos;
    public FrameLayout frameLayout;

    public MiniDebateArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mini_debate_article, container, false);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rlVote);
        relativeLayout.setVisibility(View.INVISIBLE);
        fab_tossminidebate = (FloatingActionButton) view.findViewById(R.id.fab_tail);
        frameLayout = (FrameLayout) view.findViewById(R.id.footer);
        tv_headText = (TextView) view.findViewById(R.id.tv_headText);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                footerpos = frameLayout.getY();
                frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        tv_headText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(frameLayout.getY()< footerpos+200 && frameLayout.getY()<footerpos+150){
                    frameLayout.animate().translationY(200f).withLayer();
                }else{
                    frameLayout.animate().translationY(0).withLayer();
                }

            }
        });
        fab_tossminidebate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MiniDebateActivity)MiniDebateArticleFragment.this.getActivity()).loadMiniDebateMainContentFragment();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
