package com.example.ser01.demos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;

import com.example.ser01.demos.threaddemo.ThreadDelayActivity;


/**
 * Created by Ser01 on 2017-03-29.
 */

public class upService extends Service {

    private Thread thread;
    private boolean isStart;
    /**
     * 上传成功
     */
    private static final int SUCCESS = 1;
    /**
     * 主线程handler
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    ThreadDelayActivity.tv.setText(ThreadDelayActivity.et.getText().toString());
                    break;
                default:
                    break;
            }
        }
    };

    private Runnable sRunnable = new Runnable() {
        @Override
        public void run() {
            while (isStart) {
                if (!"".equals(ThreadDelayActivity.et.getText().toString())) {
                    Message msg = handler.obtainMessage();
                    msg.what = SUCCESS;
                    handler.sendMessage(msg);
                }
                SystemClock.sleep(10000);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isStart = true;
        thread = new Thread(sRunnable);
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!thread.isAlive()) {
            isStart = true;
            thread = new Thread(sRunnable);
            thread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        isStart = false;
        super.onDestroy();
    }

}
