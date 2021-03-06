package com.zpp.myapps.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zpp.myapps.Adapter.NesAdapter;
import com.zpp.myapps.Adapter.NetworkImageHolderView;
import com.zpp.myapps.JsonMordel.Bander;
import com.zpp.myapps.JsonMordel.NesList;
import com.zpp.myapps.R;
import com.zpp.myapps.ativity.PhotoLookView;
import com.zpp.myapps.myviews.CustomSwipeToRefresh;
import com.zpp.myapps.utils.Loadmore;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by admins on 2016/4/21.
 */
public class FirstFragment extends Fragment {
    ConvenientBanner fristbannerbanner;
    private List<String> networkImages;
    String url, newsurl, urlstr;
    View view;
    Bander bander;
    View headview;
    ListView list;
    int pageIndex = 1;
    NesAdapter nesAdapter;
    NesList nesList;
    List<NesList.ListBean> beanList = new ArrayList<>();
    Loadmore loadmore;
    ArrayList<String> parray;
    CustomSwipeToRefresh swipeRefreshLayout;
    public static FirstFragment instance() {
        FirstFragment view = new FirstFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment, null);
        url = "http://appapi.rexian.cn:8080/HKCityApi/news/adsGroup?areaID=1";
        newsurl = "http://appapi01.rexian.cn/HKCityApi/news/newsFocusList?areaID=1&pageSize=10&pageIndex=" + pageIndex;
        intview();
        setList();
        getJsonString();
        getNesList(0);
        return view;
    }

    private void intview() {
        loadmore = new Loadmore();
        swipeRefreshLayout = (CustomSwipeToRefresh) view.findViewById(R.id.swipeRefreshLayout);
        headview = LayoutInflater.from(getActivity()).inflate(R.layout.headbanner, null);
        fristbannerbanner = (ConvenientBanner) headview.findViewById(R.id.fristbannerbanner);
        list = (ListView) view.findViewById(R.id.list);
        list.addHeaderView(headview);
        loadmore.loadmore(list);
        loadmore.setMyPopwindowswListener(new Loadmore.LoadmoreList() {
            @Override
            public void loadmore() {
                pageIndex++;
                newsurl = "http://appapi01.rexian.cn/HKCityApi/news/newsFocusList?areaID=1&pageSize=10&pageIndex=" + pageIndex;
                getNesList(0);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parray = new ArrayList<String>();
                for (int i = 0; i < nesList.getList().size(); i++) {
                    parray.add(nesList.getList().get(i).getPicId());
                }
                PhotoLookView.parray = parray;
                Intent intent = new Intent();
                intent.setClass(getActivity(), PhotoLookView.class);
                intent.putExtra("position", 2);
                getActivity().startActivity(intent);
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
//                Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
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
                    nesAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void setList() {
        nesAdapter = new NesAdapter(beanList, getActivity());
        list.setAdapter(nesAdapter);
    }
}
