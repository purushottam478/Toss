package com.example.rajulnahar.toss.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajulnahar.toss.Constants;
import com.example.rajulnahar.toss.Database.TableDebateClaim;
import com.example.rajulnahar.toss.Database.TableDebateFact;
import com.example.rajulnahar.toss.Database.TableUserDetails;
import com.example.rajulnahar.toss.Database.TableUserFeaturedDebate;
import com.example.rajulnahar.toss.Database.Tossdb;
import com.example.rajulnahar.toss.Fragments.MainFragment;
import com.example.rajulnahar.toss.Helper;
import com.example.rajulnahar.toss.Loaders.DebateClaimLoader;
import com.example.rajulnahar.toss.Loaders.DebateFactLoader;
import com.example.rajulnahar.toss.Objects.DebateClaim;
import com.example.rajulnahar.toss.Objects.DebateFact;
import com.example.rajulnahar.toss.Objects.UserDetails;
import com.example.rajulnahar.toss.Objects.UserFeaturedDebate;
import com.example.rajulnahar.toss.R;
import com.example.rajulnahar.toss.Interfaces.ScrollingInterface;
import com.example.rajulnahar.toss.Adapters.VerticalPagerAdapter;
import com.example.rajulnahar.toss.Containers.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ScrollingInterface {

    public List<Fragment>fragmentList;
    VerticalPagerAdapter verticalPagerAdapter;
    VerticalViewPager verticalViewPager;
    public int oldPosition;
    public TextView minidebate;
    public TextView featureddebate;
    public LinearLayout ll_nav;
    public LinearLayout ll_featured;
    public LinearLayout ll_mini;


    DebateFactLoader debateFactLoader;
    DebateClaimLoader debateClaimLoader;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        testdb();
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue.ttf");

        debateFactLoader = new DebateFactLoader(MainActivity.this);
        debateClaimLoader = new DebateClaimLoader(MainActivity.this);



        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                debateClaimLoader.loadDebateClaim("FashionAidsFeminism");
                while (!debateClaimLoader.isTaskComplete()){

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();








        minidebate = (TextView) findViewById(R.id.minidebates);
        featureddebate = (TextView) findViewById(R.id.featureddebate);

        minidebate.setTypeface(font);
        featureddebate.setTypeface(font);
        ll_nav = (LinearLayout) findViewById(R.id.nav);
        ll_featured = (LinearLayout) findViewById(R.id.ll_featuredDebate);
        ll_mini = (LinearLayout) findViewById(R.id.ll_minidebate);
        ll_featured.setAlpha((float) 0.9);
        ll_featured.setBackgroundColor(getResources().getColor(R.color.onselect));
        ll_mini.setAlpha((float) 0.4);
        ll_mini.setBackgroundColor(getResources().getColor(R.color.notselect));

        fragmentList = new ArrayList<>();
        verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
        verticalPagerAdapter = new VerticalPagerAdapter(getSupportFragmentManager());
        verticalPagerAdapter.setFragmentList(getFragmentList());
        verticalViewPager.setAdapter(verticalPagerAdapter);

        //testdb();

        minidebate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Minidebate",Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(MainActivity.this,MiniDebateActivity.class);
                startActivity(intent);


            }
        });



        verticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Constants.currentposition = position;
                if (oldPosition != 0 && oldPosition != fragmentList.size()-1)  {
                    verticalPagerAdapter.setStartPage(oldPosition);
                    oldPosition = position;
                    //verticalPagerAdapter.setStartPage(position+1);
                }
                else {
                    oldPosition = position;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    public void testdb(){
        Tossdb.getInstance(MainActivity.this).deletealltables();
        List<UserDetails> userDetailsList = new ArrayList<>();
        List<UserFeaturedDebate> userFeaturedDebateList = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.Titles);
        for(int i = 0; i < titles.length; i++){
            UserDetails userDetails = new UserDetails();
            userDetails.setUserGuid(titles[i]);
            userDetails.setUserActive(false);
            userDetailsList.add(userDetails);

            UserFeaturedDebate userFeaturedDebate = new UserFeaturedDebate();
            userFeaturedDebate.setDebateVote(titles[i]);
            userFeaturedDebate.setDebateFavourite(false);
            userFeaturedDebateList.add(userFeaturedDebate);
        }

        new TableUserDetails(MainActivity.this).addAllUserDetails(userDetailsList);
        new TableUserFeaturedDebate(MainActivity.this).addAllUserFeaturedDebate(userFeaturedDebateList);
        userDetailsList = new TableUserDetails(MainActivity.this).getAllUserDetails();
        userFeaturedDebateList = new TableUserFeaturedDebate(MainActivity.this).getAllUserFeaturedDebate();
        Helper.log("UserDetailList: " + String.valueOf(userDetailsList.size()));
        Helper.log("UserFeatureDetailList: " + String.valueOf(userFeaturedDebateList.size()));
        for(int i = 0; i < userDetailsList.size(); i++){
            Helper.log("Userdetail isUserActive: " + String.valueOf(userDetailsList.get(i).isUserActive()));
        }
     }

    public  void getData()  {
        Constants.titles = getResources().getStringArray(R.array.Titles);
        Constants.heads = getResources().getStringArray(R.array.heads);
        Constants.main_content = getResources().getStringArray(R.array.maincontent);
        Constants.tails = getResources().getStringArray(R.array.tails);
        Constants.checkstat = new int[Constants.titles.length];
        for(int i =0;i<Constants.checkstat.length;i++)  {
            Constants.checkstat[i] =0;
        }
    }

    public List<Fragment> getFragmentList()   {

        for(int i = 0;i<Constants.titles.length;i++){
            Bundle bundle = new Bundle();
            bundle.putInt("fragmentno",i);
            MainFragment mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);
            fragmentList.add(mainFragment);
        }


        return fragmentList;

    }



    @Override
    public void scroll(int position) {

        Log.e("scroll position",String.valueOf(position));
        if(position == 2)   {
            verticalViewPager.scrollvalue = true;
        }
        else    {
            verticalViewPager.scrollvalue  =false;
        }
    }

    @Override
    public void hidenav(String val) {

        if(ll_nav.getY()==0){
            ll_nav.animate().translationY(-200).withLayer();
        }else {
            ll_nav.animate().translationY(0).withLayer();
        }
    }
}
