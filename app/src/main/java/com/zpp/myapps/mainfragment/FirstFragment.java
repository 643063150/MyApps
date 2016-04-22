package com.zpp.myapps.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zpp.myapps.Adapter.NetworkImageHolderView;
import com.zpp.myapps.JsonMordel.Bander;
import com.zpp.myapps.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by admins on 2016/4/21.
 */
public class FirstFragment extends Fragment {
    ConvenientBanner fristbannerbanner;
    private List<String> networkImages;
    String url, urlstr;
    View view;
    Bander bander;
//    List<Bander.ListBean> beanList = new ArrayList<>();

    public static FirstFragment instance() {
        FirstFragment view = new FirstFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment, null);
        url = "http://appapi.rexian.cn:8080/HKCityApi/news/adsGroup?areaID=1";
        intview();
        getJsonString();
        return view;
    }

    private void intview() {
        fristbannerbanner = (ConvenientBanner) view.findViewById(R.id.fristbannerbanner);
    }

    private void getJsonString() {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                bander = JSON.parseObject(response, Bander.class);
                if (bander.getStatus() == 0) {
                    networkImages=new ArrayList<String>();
                    for (int i = 0; i < bander.getList().size(); i++) {
                        networkImages.add(bander.getList().get(i).getPicID());
                    }
                    fristbannerbanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                        @Override
                        public NetworkImageHolderView createHolder() {
                            return new NetworkImageHolderView();
                        }
                    },networkImages).setPageIndicator(new int[]{R.mipmap.indicator, R.mipmap.indicator_focused});
                }
            }
        });
    }
}
