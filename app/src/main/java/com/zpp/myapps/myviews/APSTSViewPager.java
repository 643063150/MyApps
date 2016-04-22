package com.zpp.myapps.myviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by linhonghong on 2015/8/10.
 */
public class APSTSViewPager extends ViewPager {
    private boolean mNoFocus = false; //if true, keep View don't move
    public APSTSViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public APSTSViewPager(Context context){
        this(context,null);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
//        if (mNoFocus) {
            return false;
//        }
//        return super.onInterceptTouchEvent(event);
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }


    public void setNoFocus(boolean b){
        mNoFocus = b;
    }

    @Override
    public void setCurrentItem(int item) {
//        super.setCurrentItem(item);
        setCurrentItem(item,false);//设置viewpage为不可滑动
    }
}