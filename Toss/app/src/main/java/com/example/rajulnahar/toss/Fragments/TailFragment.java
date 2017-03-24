package com.example.rajulnahar.toss.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

public class TailFragment extends Fragment {

    public FloatingActionButton fab_tail;
    public OnTossInterface onTossInterface;
    TextView tvTailText;
    public int pos;
    public Typeface font;
    TextView tvTailTitle;
    TextView tvHeadVote;
    TextView tvTailVote;
    ImageView ivHeadButton;
    ImageView ivTailButton;
    public FrameLayout fl_foot;
    public float footerpos;
    ImageView ivBack;
    public TailFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tail,container,false);
        onTossInterface = (MainFragment) getParentFragment();
        font= Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeue.ttf");
        fab_tail = (FloatingActionButton) view.findViewById(R.id.fab_tail);
        tvTailText  = (TextView) view.findViewById(R.id.tvtailtext);
        tvTailTitle = (TextView) view.findViewById(R.id.tvTailTitle);
        ivBack= (ImageView) view.findViewById(R.id.back);
        tvTailText.setTypeface(font);
        tvTailTitle.setTypeface(font);
        fl_foot  = (FrameLayout) view.findViewById(R.id.fl_hidefoot);

        tvHeadVote = (TextView) view.findViewById(R.id.tvHeadVote);
        tvTailVote = (TextView) view.findViewById(R.id.tvTailVote);
        ivHeadButton = (ImageView) view.findViewById(R.id.ivHeadVote);
        ivTailButton = (ImageView) view.findViewById(R.id.ivTailVote);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTossInterface.toss("main");
            }
        });
        tvHeadVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHeadVote.setTypeface(null, Typeface.BOLD);
                ivHeadButton.setImageResource(R.drawable.headfilled);
                tvTailVote.setTypeface(null,Typeface.NORMAL);
                ivTailButton.setImageResource(R.drawable.tailempty);
            }
        });
        ivHeadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHeadVote.setTypeface(null, Typeface.BOLD);
                ivHeadButton.setImageResource(R.drawable.headfilled);
                tvTailVote.setTypeface(null,Typeface.NORMAL);
                ivTailButton.setImageResource(R.drawable.tailempty);
            }
        });

        tvTailVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTailVote.setTypeface(null,Typeface.BOLD);
                ivTailButton.setImageResource(R.drawable.tailfilled);
                tvHeadVote.setTypeface(null,Typeface.NORMAL);
                ivHeadButton.setImageResource(R.drawable.headempty);
            }
        });
        ivTailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTailVote.setTypeface(null,Typeface.BOLD);
                ivTailButton.setImageResource(R.drawable.tailfilled);
                tvHeadVote.setTypeface(null,Typeface.NORMAL);
                ivHeadButton.setImageResource(R.drawable.headempty);
            }
        });
        Bundle bundle = getArguments();
        fl_foot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                footerpos  =fl_foot.getY();
                fl_foot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        pos = bundle.getInt("fragmentno");
        tvTailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fl_foot.getY()<footerpos+200 &&fl_foot.getY()<footerpos+150){
                    fl_foot.animate().translationY(200f).withLayer();
                }else {
                    fl_foot.animate().translationY(0f).withLayer();
                }
            }
        });
        populateView();
        fab_tail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTossInterface.toss("tail");
            }
        });
        return view;
    }

    public void populateView()
    {
        tvTailText.setText(Constants.tails[pos]);
    }

}
