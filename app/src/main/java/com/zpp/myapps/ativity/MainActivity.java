package com.zpp.myapps.ativity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.zpp.myapps.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_main);
        turnactivity(3000);
    }
    //time秒后跳转页面
    private void turnactivity(long time){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,HomeActivity.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        },time);

    }
}
