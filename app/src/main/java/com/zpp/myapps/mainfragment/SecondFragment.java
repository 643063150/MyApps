package com.zpp.myapps.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpp.myapps.R;

/**
 * Created by admins on 2016/4/21.
 */
public class SecondFragment extends Fragment {

    View view;
    public static SecondFragment instance() {
        SecondFragment view = new SecondFragment();
        return view;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment, null);
        intview();
        return view;
    }
    private void intview(){

    }
}
