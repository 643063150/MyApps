package com.zpp.myapps.ativity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zpp.myapps.R;
import com.zpp.myapps.fragment.ImageDetailFragment;
import com.zpp.myapps.myviews.ZViewPager;
import com.zpp.myapps.utils.StatusBar;

import java.util.ArrayList;

/**
 * Created by admins on 2016/4/23.
 * 图片浏览类
 * positio为传进来的图片下表 不传为0 ,type为图片地址格式 0为网址 1为本地图片 默认为0
 */
public class PhotoLookView extends AppCompatActivity {
    ZViewPager viewpage;
    Toolbar toolbar;
    TextView title;
    public static ArrayList<String> parray = new ArrayList<String>();
    int position, type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.StatusBar(this);
        setContentView(R.layout.photolookview);
        position = getIntent().getIntExtra("position", 0);
        type = getIntent().getIntExtra("type", 0);
        intview();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (type) {
            case 0:
                break;
            case 1:
                getMenuInflater().inflate(R.menu.delect_menu, menu);
                break;
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void intview() {
        title = (TextView) findViewById(R.id.title);
        toolbar = (Toolbar) findViewById(R.id.mytollbar);
        viewpage = (ZViewPager) findViewById(R.id.photoviewpage);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("图片浏览");
        final ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), parray);
        viewpage.setAdapter(mAdapter);
        viewpage.setCurrentItem(position);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delect_bar:
                        parray.remove(viewpage.getCurrentItem());
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        parray.clear();
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ArrayList<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url;
            if (type == 0) {
                url = fileList.get(position);
            } else {
                url = "file://" + fileList.get(position);
            }
            return ImageDetailFragment.newInstance(url);
        }
    }
}
