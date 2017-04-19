package com.example.ser01.demos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Ser01 on 2017-03-13.
 *
 */

public class LeaderActivity extends AppCompatActivity{

    ImageView iv;
    LinearLayout ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        iv = (ImageView) findViewById(R.id.iv);
        ll = (LinearLayout) findViewById(R.id.ll);
        View view = getLayoutInflater().inflate(R.layout.activity_leader, null);
        scaleImage(LeaderActivity.this, view, R.mipmap.ic_launcher);
    }

    public void scaleImage(final Activity activity, final View view, int drawableResId) {

        // 获取屏幕的高宽
        Point outSize = new Point();
        activity.getWindow().getWindowManager().getDefaultDisplay().getSize(outSize);

        // 解析将要被处理的图片
        Bitmap resourceBitmap =
                BitmapFactory.decodeResource(activity.getResources(), drawableResId);

        if (resourceBitmap == null) {
            return;
        }

        // 开始对图片进行拉伸或者缩放

        // 使用图片的缩放比例计算将要放大的图片的高度
        int bitmapScaledHeight =
                Math.round(resourceBitmap.getHeight() * outSize.x * 1.0f
                        / resourceBitmap.getWidth());

        // 以屏幕的宽度为基准，如果图片的宽度比屏幕宽，则等比缩小，如果窄，则放大
        final Bitmap scaledBitmap =
                Bitmap.createScaledBitmap(
                        resourceBitmap, outSize.x, bitmapScaledHeight, false);

        // 当UI绘制完毕，我们对图片进行处理
        int viewHeight = view.getMeasuredHeight();

        // 计算将要裁剪的图片的顶部以及底部的便宜量
        int offset = (scaledBitmap.getHeight() - viewHeight) / 2;

        // 对图片以中心进行裁剪，裁剪出的图片就是非常适合做引导页的图片了
        Bitmap finallyBitmap =
                Bitmap.createBitmap(
                        scaledBitmap, 0, offset, scaledBitmap.getWidth(),
                        scaledBitmap.getHeight() - offset * 2);

        // 设置图片显示
        view.setBackground(
                new BitmapDrawable(activity.getResources(), finallyBitmap));
        view.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {

            }
        });
    }
}
