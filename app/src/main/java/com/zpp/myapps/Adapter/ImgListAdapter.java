package com.zpp.myapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zpp.myapps.JsonMordel.NesList;
import com.zpp.myapps.R;
import com.zpp.myapps.utils.ImageUtils;

import java.util.List;

/**
 * Created by admins on 2016/4/25.
 */
public class ImgListAdapter extends BaseAdapter {
    List<NesList.ListBean> nesList;
    Context context;
    ImageLoader imageLoader;
    ImageUtils imageUtils;
    DisplayImageOptions options;
    ImageLoadingListener animateFirstListener;
    public ImgListAdapter(List<NesList.ListBean> nesList, Context context){
        this.nesList = nesList;
        this.context = context;
        imageUtils = new ImageUtils();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        animateFirstListener = new ImageUtils.AnimateFirstDisplayListener();
    }
    @Override
    public int getCount() {
        return nesList.size();
    }

    @Override
    public Object getItem(int position) {
        return nesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        getItem getItem = new getItem();
        convertView= LayoutInflater.from(context).inflate(R.layout.img_item,null);
        getItem.title=(TextView)convertView.findViewById(R.id.title);
        getItem.logoimg=(ImageView) convertView.findViewById(R.id.logo);
        getItem.title.setText(nesList.get(position).getTitle());
        String url=nesList.get(position).getPicId();
        options=imageUtils.setcenterOptions();
        imageLoader.displayImage(url, getItem.logoimg, options);
        return convertView;
    }
    private class getItem{
        ImageView logoimg;
        TextView title;
    }
}
