package com.zpp.myapps.utils;

import android.widget.AbsListView;
import android.widget.GridView;

/**
 * Created by admins on 2016/4/25.
 */
public class GridLoadmore {
    LoadmoreList loadmoreList;
    public void loadmore(GridView gridView){
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            startLoadMore();
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }
    public void setMyPopwindowswListener(LoadmoreList l) {
        loadmoreList = l;
    }
    public interface LoadmoreList {
        public void loadmore();
    }
    private void startLoadMore() {

        loadmoreList.loadmore();

    }
}
