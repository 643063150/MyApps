package com.zpp.myapps.ativity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zpp.myapps.R;
import com.zpp.myapps.utils.StatusBar;

/**
 * Created by admins on 2016/4/22.
 */
public class MyTestActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar mytollbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.StatusBar(this);
        setContentView(R.layout.mttest_layout);
        intview();
    }

    private void intview() {
        mytollbar = (Toolbar) findViewById(R.id.mytollbar);
        mytollbar.setTitle("ToolBarTest");
//        mytollbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(mytollbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
