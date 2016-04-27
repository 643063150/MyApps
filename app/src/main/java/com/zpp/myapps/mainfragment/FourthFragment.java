package com.zpp.myapps.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.zpp.myapps.R;
import com.zpp.myapps.utils.mDateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by admins on 2016/4/21.
 */
public class FourthFragment extends Fragment implements View.OnClickListener {
    View view;
    TextView timepicker;
    TimePickerView pvTime;

    public static FourthFragment instance() {
        FourthFragment view = new FourthFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fourth_fragment, null);
        intview();

        return view;
    }

    private void intview() {
        //时间选择器
        pvTime = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        timepicker = (TextView) view.findViewById(R.id.timepicker);
        timepicker.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 70, calendar.get(Calendar.YEAR));
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                timepicker.setText(mDateUtil.getTime(date));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.timepicker:
                pvTime.show();
                break;
        }
    }
}
