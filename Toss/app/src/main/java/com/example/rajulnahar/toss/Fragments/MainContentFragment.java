package com.example.rajulnahar.toss.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajulnahar.toss.Constants;
import com.example.rajulnahar.toss.Interfaces.OnTossInterface;
import com.example.rajulnahar.toss.R;

import java.util.Random;

public class MainContentFragment extends Fragment {
    public FloatingActionButton fab_main;
    OnTossInterface onTossInterface;
    public Random random;
    public int pageNo;
    public TextView tvtitle;;
    public TextView tvmaincontent;
    public TextView tvHead;
    public TextView tvTail;
    public FrameLayout flImage;
    public RelativeLayout relativeLayout;
    public View view;
    public Typeface font;
    public LinearLayout ll_hidenav;
    public MainContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
         font= Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeue.ttf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_content,container,false);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rlVote);
        fab_main = (FloatingActionButton) view.findViewById(R.id.fab_main);
        tvtitle = (TextView) view.findViewById(R.id.tv_title);
        ll_hidenav = (LinearLayout) view.findViewById(R.id.ll_hidenav);
        tvmaincontent = (TextView) view.findViewById(R.id.tv_main_content);
        tvmaincontent.setTypeface(font);
        flImage = (FrameLayout) view.findViewById(R.id.flImage);
        Bundle bundle = getArguments();
        pageNo = bundle.getInt("fragmentno");
        populateView();
        //Log.e("Rajul","PageNum: " + String.valueOf(pageNo) + " Flag: " + String.valueOf(Constants.checkstat[pageNo]));
        if(Constants.checkstat[pageNo]== 1){
            relativeLayout.setVisibility(View.VISIBLE);
            //fab_main.setImageResource(R.drawable.votetransparent);
        }
        else {
            relativeLayout.setVisibility(View.INVISIBLE);
        }

        ll_hidenav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTossInterface.hide("yes");
            }
        });
        flImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTossInterface.hide("yes");
            }
        });
        tvHead = (TextView) view.findViewById(R.id.tvRightVote);
        tvTail = (TextView) view.findViewById(R.id.tvLeftVote);
        tvTail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainContentFragment.this.getContext(),"Voted Tail",Toast.LENGTH_SHORT).show();
            }
        });
        tvHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainContentFragment.this.getContext(),"Voted head",Toast.LENGTH_SHORT).show();
            }
        });
        onTossInterface = (MainFragment)getParentFragment();
        random = new Random();
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = random.nextFloat();
                if(x<= 0.5){
                    onTossInterface.toss("tail");
                }
                else {
                    onTossInterface.toss("head");
                }

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.e("onviewrestored",String.valueOf(pageNo));

    }

    public void updateUI()  {
        if(relativeLayout != null){
            relativeLayout.setVisibility(View.VISIBLE);
            //fab_main.setImageResource(R.drawable.votetransparent);
        }

    }

    public void populateView()  {
        tvtitle.setText(Constants.titles[pageNo]);
        tvmaincontent.setText(Constants.main_content[pageNo]);
    }

}
