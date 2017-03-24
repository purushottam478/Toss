package com.example.rajulnahar.toss.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rajulnahar.toss.Constants;
import com.example.rajulnahar.toss.Interfaces.OnTossInterface;
import com.example.rajulnahar.toss.R;


public class HeadFragment extends Fragment {
    public FloatingActionButton fab_head;
    public OnTossInterface onTossInterface;
    TextView tvHeadText;
    public int pos;
    TextView tvHeadTitle;
    TextView tvHeadVote;
    TextView tvTailVote;
    ImageView ivHeadButton;
    ImageView ivTailButton;
    ImageView ivBack;
    public FrameLayout frameLayout;
    public float footerpos;
    public Typeface font;
    public HeadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_head, container,false);
        fab_head = (FloatingActionButton) view.findViewById(R.id.fab_head);
        font= Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeue.ttf");
        tvHeadText  = (TextView) view.findViewById(R.id.tv_headText);
        tvHeadTitle = (TextView) view.findViewById(R.id.tvHeadTitle);
        tvHeadTitle.setTypeface(font);
        tvHeadText.setTypeface(font);
        frameLayout = (FrameLayout) view.findViewById(R.id.fl_hidefoot);
        tvHeadVote = (TextView) view.findViewById(R.id.tvHeadVote);
        tvTailVote = (TextView) view.findViewById(R.id.tvTailVote);
        ivHeadButton = (ImageView) view.findViewById(R.id.ivHeadVote);
        ivTailButton = (ImageView) view.findViewById(R.id.ivTailVote);
        ivBack = (ImageView) view.findViewById(R.id.back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTossInterface.toss("main");
            }
        });
        tvHeadVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHeadVote.setTypeface(null,Typeface.BOLD);
                ivHeadButton.setImageResource(R.drawable.headfilled);
                ivTailButton.setImageResource(R.drawable.tailempty);
                tvTailVote.setTypeface(null,Typeface.NORMAL);
            }
        });
        ivHeadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHeadVote.setTypeface(null,Typeface.BOLD);
                ivHeadButton.setImageResource(R.drawable.headfilled);
                ivTailButton.setImageResource(R.drawable.tailempty);
                tvTailVote.setTypeface(null,Typeface.NORMAL);
            }
        });

        tvTailVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTailVote.setTypeface(null,Typeface.BOLD);
                ivTailButton.setImageResource(R.drawable.tailfilled);
                tvHeadVote.setTypeface(null, Typeface.NORMAL);
                ivHeadButton.setImageResource(R.drawable.headempty);
            }
        });
        ivTailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTailVote.setTypeface(null,Typeface.BOLD);
                ivTailButton.setImageResource(R.drawable.tailfilled);
                tvHeadVote.setTypeface(null, Typeface.NORMAL);
                ivHeadButton.setImageResource(R.drawable.headempty);
            }
        });

        Bundle bundle = getArguments();
        pos = bundle.getInt("fragmentno");
        populateView();

        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                footerpos = frameLayout.getY();
                frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        onTossInterface = (MainFragment)getParentFragment();
        fab_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTossInterface.toss("head");
            }
        });
        tvHeadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("pos yslide",String.valueOf(footerpos));
                Log.e("pos yframe",String.valueOf(frameLayout.getY()));
                if(frameLayout.getY() < footerpos+200){
                    frameLayout.animate().translationY(200f).withLayer();
                }else{
                    frameLayout.animate().translationY(0f).withLayer();
                }
            }
        });
        return view;
    }

    public void populateView()  {
        tvHeadText.setText(Constants.heads[pos]);
    }

}
