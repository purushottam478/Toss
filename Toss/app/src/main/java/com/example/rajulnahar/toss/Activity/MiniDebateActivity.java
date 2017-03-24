package com.example.rajulnahar.toss.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rajulnahar.toss.Fragments.MiniDebateArticleFragment;
import com.example.rajulnahar.toss.Fragments.MiniDebateMainContentFragment;
import com.example.rajulnahar.toss.R;

import java.util.ArrayList;
import java.util.List;

public class MiniDebateActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_debate);
        fragmentManager = getSupportFragmentManager();
        loadMiniDebateMainContentFragment();
        //testdb();


    }

   /* public void testdb(){
        List<MiniDebate> miniDebateList = new ArrayList<>();
        MiniDebate miniDebate = new MiniDebate();
        String[] arrcontent = getResources().getStringArray(R.array.maincontent);
        String[] arrtitle = getResources().getStringArray(R.array.Titles);
        for (int i = 0;i<arrcontent.length;i++){
            miniDebate.setMainTitle(arrtitle[i]);
            miniDebate.setMainText(arrcontent[i]);
            miniDebateList.add(miniDebate);
        }
        MiniClaimsTable miniClaimsTable = new MiniClaimsTable(this);
        miniClaimsTable.addAllMiniClaims(miniDebateList);
    }*/

    public  void loadMiniDebateMainContentFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_mini_debate,new MiniDebateMainContentFragment(),"toss_minidebatemaincontent");
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void loadMiniDebateArticle() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_mini_debate, new MiniDebateArticleFragment(),"toss_minidebatearticle");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setCustomAnimations(R.anim.flip_left_in,R.anim.flip_left_out);
        //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
