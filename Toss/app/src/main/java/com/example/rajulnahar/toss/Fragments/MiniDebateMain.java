package com.example.rajulnahar.toss.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rajulnahar.toss.Activity.MiniDebateActivity;
import com.example.rajulnahar.toss.R;

public class MiniDebateMain extends Fragment {
    public LinearLayout ll_hidenav;
    public FloatingActionButton fab_main;
    public FrameLayout flImage;

    public MiniDebateMain() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_mini_debate_main, container, false);
        ll_hidenav = (LinearLayout) view.findViewById(R.id.ll_hidenav);
        flImage = (FrameLayout) view.findViewById(R.id.flImage);
        flImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MiniDebateMainContentFragment)MiniDebateMain.this.getParentFragment()).hidenav();
            }
        });
        ll_hidenav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ((MiniDebateMainContentFragment)MiniDebateMain.this.getParentFragment()).hidenav();
            }
        });
        fab_main = (FloatingActionButton) view.findViewById(R.id.fab_main);
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MiniDebateMain.this.getContext(),"flip!",Toast.LENGTH_SHORT).show();

                ((MiniDebateActivity)((MiniDebateMainContentFragment)MiniDebateMain.this.getParentFragment()).getActivity()).loadMiniDebateArticle();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
