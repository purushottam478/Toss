package com.example.rajulnahar.toss.Containers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Rajul Nahar on 29-12-2016.
 */

public class VerticalViewPager extends ViewPager {

    public Boolean scrollvalue = true;

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new VerticalPageTransformer());
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        private   float minscale = 0.75f;

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            }
            else if(position<=0){
                view.setAlpha(1);
                view.setTranslationX(view.getWidth()* -position);
                view.setTranslationY(view.getHeight()*position);
                view.setScaleX(1);
                view.setScaleY(1);
            }
            else if (position <= 1) { // [-1,1]
                view.setAlpha(1);

                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position);

                //set Y position to swipe in from top
                float yPosition = position * view.getHeight();
                view.setTranslationY(0);
                float scalefactor = minscale+(1-minscale)*(1-Math.abs(position));
                view.setScaleX(scalefactor);
                view.setScaleY(scalefactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * height;
        float newY = (ev.getX() / width) * width;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        if(scrollvalue) {
            boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
            swapXY(ev);
            // return touch coordinates to original reference frame for any child views
            return intercepted;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(scrollvalue)
        return super.onTouchEvent(swapXY(ev)) && scrollvalue;
        else
            return false;
    }

}