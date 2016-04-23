package com.zpp.myapps.ativity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.zpp.myapps.R;
import com.zpp.myapps.utils.StatusBar;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by admins on 2016/4/23.
 */
public class ErrorActivity extends AppCompatActivity implements OnItemClickListener{
    TextView txt;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.StatusBar(this);
        setContentView(R.layout.mttest_layout);
        txt=(TextView)findViewById(R.id.title);
        toolbar=(Toolbar)findViewById(R.id.mytollbar);
        txt.setText("错误");
        setSupportActionBar(toolbar);
        writeSDcard();
        alertShow2();
    }
    private void writeSDcard() {
        String error= CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent());
        File sd = Environment.getExternalStorageDirectory();
        String path = sd.getPath() + "/Myapps/Logs/";
        SimpleDateFormat    formatter    =   new SimpleDateFormat("yyyyMMddHH:mm:ss");
        Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        String    str    =    formatter.format(curDate);
        String fileName =str+"crash";
        try {
            // 判断是否存在SD卡
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                // 获取SD卡的目录
                File file = Environment.getExternalStorageDirectory();
                FileOutputStream fileW = new FileOutputStream(path +fileName+".log");
                fileW.write(error.getBytes());
                fileW.close();
            }else{
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void alertShow2() {
        new AlertView("提示", "app发生异常,log已保存点击确定退出", null, new String[]{"确定"}, null, this, AlertView.Style.Alert, this).show();
    }

    @Override
    public void onItemClick(Object o, int position) {
        finish();
    }
}
