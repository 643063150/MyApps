package com.zpp.myapps.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.zpp.myapps.Adapter.ImgGridAdapter;
import com.zpp.myapps.R;
import com.zpp.myapps.ativity.PhotoLookView;
import com.zpp.myapps.myviews.MyGridView;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by admins on 2016/4/21.
 */
public class ThirdFragment extends Fragment {
    View view;
    private static final int REQUEST_IMAGE = 2;
    MyGridView photogrid;
    private RadioGroup mChoiceMode, mShowCamera;
    private EditText mRequestNum;
    ImgGridAdapter imgGridAdapter;
    private ArrayList<String> mSelectPath;

    public static ThirdFragment instance() {
        ThirdFragment view = new ThirdFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.third_fragment, null);
        intview();
        return view;
    }

    private void intview() {
        photogrid = (MyGridView) view.findViewById(R.id.photogrid);
        mChoiceMode = (RadioGroup) view.findViewById(R.id.choice_mode);
        mShowCamera = (RadioGroup) view.findViewById(R.id.show_camera);
        mRequestNum = (EditText) view.findViewById(R.id.request_num);
        mChoiceMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.multi) {
                    mRequestNum.setEnabled(true);
                } else {
                    mRequestNum.setEnabled(false);
                    mRequestNum.setText("");
                }
            }
        });
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

                if (mChoiceMode.getCheckedRadioButtonId() == R.id.single) {
                    selectedMode = MultiImageSelectorActivity.MODE_SINGLE;
                } else {
                    selectedMode = MultiImageSelectorActivity.MODE_MULTI;
                }

                boolean showCamera = mShowCamera.getCheckedRadioButtonId() == R.id.show;

                int maxNum = 9;
                if (!TextUtils.isEmpty(mRequestNum.getText())) {
                    maxNum = Integer.valueOf(mRequestNum.getText().toString());
                }

                Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);
                // 是否显示拍摄图片
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, showCamera);
                // 最大可选择图片数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
                // 选择模式
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);
                // 默认选择
                if (mSelectPath != null && mSelectPath.size() > 0) {
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);
                }
                startActivityForResult(intent, REQUEST_IMAGE);

            }
        });
        photogrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoLookView.parray=mSelectPath;
                Intent intent = new Intent();
                intent.setClass(getActivity(), PhotoLookView.class);
                intent.putExtra("position", position);
                intent.putExtra("type", 1);
                getActivity().startActivity(intent);
            }
        });
    }

    private void setImgGridAdapter() {
        imgGridAdapter = new ImgGridAdapter(mSelectPath, getActivity());
        photogrid.setAdapter(imgGridAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            imgGridAdapter.notifyDataSetChanged();
        }catch (Exception e){

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == getActivity().RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                setImgGridAdapter();
            }
        }
    }
}
