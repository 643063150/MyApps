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
public class FirstFragment extends Fragment {

    View view;
    public static FirstFragment instance() {
        FirstFragment view = new FirstFragment();
        return view;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment, null);
        return view;
    }
    private void intview(){

    }
}
