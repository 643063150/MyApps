package com.zpp.myapps.appservice;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by admins on 2016/4/21.
 */
public class MyApplcation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化imgload
        File diskCache = StorageUtils.getOwnCacheDirectory(this.getApplicationContext(),
                "Myapps/Cache");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                this.getApplicationContext()).memoryCacheExtraOptions(400, 800).discCacheFileCount(200)
                .discCache(new UnlimitedDiskCache(diskCache)).build();
        ImageLoader.getInstance().init(configuration);
    }
}
