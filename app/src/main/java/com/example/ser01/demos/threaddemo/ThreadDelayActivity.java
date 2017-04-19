package com.example.ser01.demos.threaddemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ser01.demos.R;
import com.example.ser01.demos.service.upService;

/**
 * Created by Ser01 on 2017-03-29.
 *
 */

public class ThreadDelayActivity extends AppCompatActivity implements View.OnClickListener{

    public static EditText et;
    public static TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_delay);
        et = (EditText) findViewById(R.id.et_thread_delay);
        tv = (TextView) findViewById(R.id.tv_thread_delay);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                Intent intent = new Intent(this, upService.class);
                startService(intent);
                break;
            case R.id.btn_end:
                Intent intent2 = new Intent(this, upService.class);
                stopService(intent2);
                break;
        }
    }
}
