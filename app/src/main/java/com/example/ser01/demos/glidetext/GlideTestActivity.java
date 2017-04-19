package com.example.ser01.demos.glidetext;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ser01.demos.R;
import com.example.ser01.demos.view.MyDatePickerDialog;

import java.util.Calendar;

/**
 *
 * Created by Ser01 on 2017-04-03.
 *
 */

public class GlideTestActivity extends AppCompatActivity implements View.OnClickListener{

    String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
    ImageView imageView;
    private EditText et_date;
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        imageView = (ImageView) findViewById(R.id.iv_glide_test);
        et_date = (EditText) findViewById(R.id.et_date);
        /** 上门取货日期初始化 */
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        et_date.setText(new StringBuffer()
                .append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_glide_test)
            Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageView);
        else if(v.getId() == R.id.et_date)
            showDialog(DATE_DIALOG);
    }

    /**
     * 日期选择对话框创建
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new MyDatePickerDialog(
                        this, R.style.AppTheme_AppDate,
                        mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    /**
     * 日期选择监听
     */
    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            et_date.setText(
                    new StringBuffer()
                            .append(mYear).append("-")
                            .append(mMonth + 1).append("-").append(mDay));


        }
    };

}
