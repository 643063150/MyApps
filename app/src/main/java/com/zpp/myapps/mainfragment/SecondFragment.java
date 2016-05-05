package com.zpp.myapps.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zpp.myapps.Adapter.ImgListAdapter;
import com.zpp.myapps.Adapter.NetworkImageHolderView;
import com.zpp.myapps.JsonMordel.Bander;
import com.zpp.myapps.JsonMordel.NesList;
import com.zpp.myapps.R;
import com.zpp.myapps.myviews.CustomSwipeToRefresh;
import com.zpp.myapps.utils.GridLoadmore;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;
import okhttp3.Call;

/**
 * Created by admins on 2016/4/21.
 */
public class SecondFragment extends Fragment {
    GridViewWithHeaderAndFooter mygrid;
    ConvenientBanner fristbannerbanner;
    View view,headview;
    ImgListAdapter imgListAdapter;
    String url, newsurl, urlstr;
    int pageIndex = 1;
    NesList nesList;
    Bander bander;
    private List<String> networkImages;
    List<NesList.ListBean> beanList = new ArrayList<>();
    CustomSwipeToRefresh swipeRefreshLayout;
    GridLoadmore gridLoadmore;
    public static SecondFragment instance() {
        SecondFragment view = new SecondFragment();
        return view;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_fragment, null);
        url = "http://appapi.rexian.cn:8080/HKCityApi/news/adsGroup?areaID=1";
        newsurl = "http://appapi01.rexian.cn/HKCityApi/news/newsFocusList?areaID=1&pageSize=10&pageIndex=" + pageIndex;
        intview();
        setList();
        getJsonString();
        getNesList(0);
        return view;
    }
    private void intview(){
        gridLoadmore=new GridLoadmore();
        mygrid=(GridViewWithHeaderAndFooter)view.findViewById(R.id.mygrid);
        headview = LayoutInflater.from(getActivity()).inflate(R.layout.headbanner, null);
        fristbannerbanner = (ConvenientBanner) headview.findViewById(R.id.fristbannerbanner);
        mygrid.addHeaderView(headview);
        swipeRefreshLayout = (CustomSwipeToRefresh) view.findViewById(R.id.swipeRefreshLayout);
        gridLoadmore.loadmore(mygrid);
        gridLoadmore.setMyPopwindowswListener(new GridLoadmore.LoadmoreList() {
            @Override
            public void loadmore() {
                pageIndex++;
                newsurl = "http://appapi01.rexian.cn/HKCityApi/news/newsFocusList?areaID=1&pageSize=10&pageIndex=" + pageIndex;
                getNesList(0);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                newsurl = "http://appapi01.rexian.cn/HKCityApi/news/newsFocusList?areaID=1&pageSize=10&pageIndex=" + pageIndex;
                getJsonString();
                getNesList(1);
            }
        });
    }
    private void getJsonString() {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Snackbar.make(view, R.string.interror, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }

            @Override
            public void onResponse(String response) {
                bander = JSON.parseObject(response, Bander.class);
                if (bander.getStatus() == 0) {
                    networkImages = new ArrayList<String>();
                    for (int i = 0; i < bander.getList().size(); i++) {
                        networkImages.add(bander.getList().get(i).getPicID());
                    }
                    fristbannerbanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                        @Override
                        public NetworkImageHolderView createHolder() {
                            return new NetworkImageHolderView();
                        }
                    }, networkImages).setPageIndicator(new int[]{R.mipmap.indicator, R.mipmap.indicator_focused});
                }
            }
        });
    }
    private void getNesList(final int type) {
        OkHttpUtils.get().url(newsurl).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Snackbar.make(view, R.string.interror, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(String response) {
                nesList = JSON.parseObject(response, NesList.class);
                if (nesList.getStatus() == 0) {
                    if (type == 1) {
                        beanList.clear();
                    }
                    beanList.addAll(nesList.getList());
                    imgListAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }
    private void setList() {
        imgListAdapter = new ImgListAdapter(beanList, getActivity());
        mygrid.setAdapter(imgListAdapter);
    }
}
