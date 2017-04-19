package com.example.ser01.demos.seconddemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ser01.demos.MainActivity;
import com.example.ser01.demos.R;
import com.example.ser01.demos.thirddemo.ThirdActivity;

/**
 * Created by Ser01 on 2017-02-06.
 *
 */

public class SecondActivity extends BaseActivity implements View.OnClickListener{

    TextView tvDate,tvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();
    }

    //初始化控件
    private void initView() {
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDateDialog();
            }
        });
        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCityDialog();
            }
        });
    }

    //Choose Date
    public void chooseDateDialog() {
        final ChooseDateUtil dateUtil = new ChooseDateUtil();
        int[] oldDateArray = {2016, 01, 01};
        dateUtil.createDialog(this, oldDateArray, new ChooseDateInterface() {
            @Override
            public void sure(int[] newDateArray) {
                tvDate.setText(newDateArray[0] + "-" + newDateArray[1] + "-" + newDateArray[2]);
            }
        });
    }

    //Choose Date
    public void chooseCityDialog() {
        final ChooseCityUtil cityUtil = new ChooseCityUtil();
        String[] oldCityArray = tvCity.getText().toString().split("-");
        cityUtil.createDialog(this, oldCityArray, new ChooseCityInterface() {
            @Override
            public void sure(String[] newCityArray) {
                tvCity.setText(newCityArray[0] + "-" + newCityArray[1] + "-" + newCityArray[2]);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_second) {
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(intent);
        }
    }
}
