package com.zpp.myapps.ativity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.zpp.myapps.R;
import com.zpp.myapps.mainfragment.FirstFragment;
import com.zpp.myapps.mainfragment.FourthFragment;
import com.zpp.myapps.mainfragment.SecondFragment;
import com.zpp.myapps.mainfragment.ThirdFragment;
import com.zpp.myapps.myviews.APSTSViewPager;
import com.zpp.myapps.utils.StatusBar;

/**
 * Created by linhonghong on 2015/8/11.
 */
public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    public AdvancedPagerSlidingTabStrip mAPSTS;
    public APSTSViewPager mVP;
    Toolbar mytollbar;
    TextView title;
    private static final int VIEW_FIRST = 0;
    private static final int VIEW_SECOND = 1;
    private static final int VIEW_THIRD = 2;
    private static final int VIEW_FOURTH = 3;

    private static final int VIEW_SIZE = 4;

    private FirstFragment mFirstFragment = null;
    private SecondFragment mSecondFragment = null;
    private ThirdFragment mThirdFragment = null;
    private FourthFragment mFourthFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.StatusBar(this);
        setContentView(R.layout.home_layout);
        findViews();

    }

    private void findViews() {
        mytollbar=(Toolbar)findViewById(R.id.mytollbar);
        title=(TextView)findViewById(R.id.title);
        mAPSTS = (AdvancedPagerSlidingTabStrip) findViewById(R.id.tabs);
        mVP = (APSTSViewPager) findViewById(R.id.vp_main);
        mytollbar.setNavigationIcon(null);
        title.setText("第一个页面");
        setSupportActionBar(mytollbar);
        init();
        mytollbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search_bar:
                        Toast.makeText(HomeActivity.this,"搜索",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.share_bar:
                        Toast.makeText(HomeActivity.this,"分享",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (mVP.getCurrentItem()){
            case VIEW_FIRST:
                getMenuInflater().inflate(R.menu.search_menu,menu);
                menu.findItem(R.id.share_bar).setVisible(false);
                menu.findItem(R.id.search_bar).setVisible(true);
                break;
            case VIEW_SECOND:
                getMenuInflater().inflate(R.menu.search_menu,menu);
                menu.findItem(R.id.share_bar).setVisible(true);
                menu.findItem(R.id.search_bar).setVisible(false);
                break;
            case VIEW_THIRD:
                break;
            case VIEW_FOURTH:
                break;
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void init() {
        mVP.setOffscreenPageLimit(VIEW_SIZE);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mVP.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        adapter.notifyDataSetChanged();
        mAPSTS.setViewPager(mVP);
        mAPSTS.setOnPageChangeListener(this);
        mVP.setCurrentItem(VIEW_FIRST);
//        mAPSTS.showDot(VIEW_FIRST,"99+");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case VIEW_FIRST:
                title.setText("第一个页面");
                invalidateOptionsMenu();
                break;
            case VIEW_SECOND:
                title.setText("第二个页面");
                invalidateOptionsMenu();
                break;
            case VIEW_THIRD:
                title.setText("第三个页面");
                invalidateOptionsMenu();
                break;
            case VIEW_FOURTH:
                title.setText("第四个页面");
                invalidateOptionsMenu();
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class FragmentAdapter extends FragmentStatePagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position >= 0 && position < VIEW_SIZE) {
                switch (position) {
                    case VIEW_FIRST:
                        if (null == mFirstFragment)
                            mFirstFragment = FirstFragment.instance();
                        return mFirstFragment;

                    case VIEW_SECOND:
                        if (null == mSecondFragment)
                            mSecondFragment = SecondFragment.instance();
                        return mSecondFragment;

                    case VIEW_THIRD:
                        if (null == mThirdFragment)
                            mThirdFragment = ThirdFragment.instance();
                        return mThirdFragment;

                    case VIEW_FOURTH:
                        if (null == mFourthFragment)
                            mFourthFragment = FourthFragment.instance();
                        return mFourthFragment;
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return VIEW_SIZE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position >= 0 && position < VIEW_SIZE) {
                switch (position) {
                    case VIEW_FIRST:
                        return "first";
                    case VIEW_SECOND:
                        return "second";
                    case VIEW_THIRD:
                        return "third";
                    case VIEW_FOURTH:
                        return "fourth";
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public Integer getPageIcon(int index) {
            if (index >= 0 && index < VIEW_SIZE) {
                switch (index) {
                    case VIEW_FIRST:
                        return R.mipmap.home_main_icon_n;
                    case VIEW_SECOND:
                        return R.mipmap.home_categry_icon_n;
                    case VIEW_THIRD:
                        return R.mipmap.home_live_icon_n;
                    case VIEW_FOURTH:
                        return R.mipmap.home_mine_icon_n;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Integer getPageSelectIcon(int index) {
            if (index >= 0 && index < VIEW_SIZE) {
                switch (index) {
                    case VIEW_FIRST:
                        return R.mipmap.home_main_icon_f_n;
                    case VIEW_SECOND:
                        return R.mipmap.home_categry_icon_f_n;
                    case VIEW_THIRD:
                        return R.mipmap.home_live_icon_f_n;
                    case VIEW_FOURTH:
                        return R.mipmap.home_mine_icon_f_n;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Rect getPageIconBounds(int position) {
            return null;
        }
    }
}
