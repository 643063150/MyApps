package com.zpp.myapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zpp.myapps.R;
import com.zpp.myapps.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by admins on 2016/4/26.
 */
public class ImgGridAdapter extends BaseAdapter {
    private ArrayList<String> mSelectPath;
    Context context;
    ImageLoader imageLoader;
    ImageUtils imageUtils;
    DisplayImageOptions options;
    ImageLoadingListener animateFirstListener;
    public ImgGridAdapter(ArrayList<String> mSelectPath, Context context){
        this.mSelectPath=mSelectPath;
        this.context=context;
        imageUtils = new ImageUtils();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        animateFirstListener = new ImageUtils.AnimateFirstDisplayListener();
    }
    @Override
    public int getCount() {
        return mSelectPath.size();
    }

    @Override
    public Object getItem(int position) {
        return mSelectPath.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        getItem getItem = new getItem();
        convertView= LayoutInflater.from(context).inflate(R.layout.imggrid_item,null);
        getItem.img=(ImageView)convertView.findViewById(R.id.img);
        String url="file://"+mSelectPath.get(position);
        options=imageUtils.setcenterOptions();
        imageLoader.displayImage(url, getItem.img, options);
        return convertView;
    }
    private class getItem{
    ImageView img;
    }
}
