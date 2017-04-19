package com.example.ser01.demos.sevendemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.ser01.demos.R;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;

/**
 * Created by Ser01 on 2017-03-22.
 *
 */

public class seven extends AppCompatActivity {

    /**
     * 将从Message中获取的，表示图片的字符串解析为Bitmap对象
     *
     * @param picStrInMsg
     * @return
     */
    public static Bitmap decodeImg(String picStrInMsg) {
        Bitmap bitmap = null;
        byte[] imgByte = null;
        InputStream input = null;
        try {
            imgByte = Base64.decode(picStrInMsg, Base64.DEFAULT);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            input = new ByteArrayInputStream(imgByte);
            SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(input, null, options));
            bitmap = (Bitmap) softRef.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (imgByte != null) {
                imgByte = null;
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    TextView firstTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);
        firstTxt = (TextView) findViewById(R.id.hello_word_txt);
        //此方法会加载onMeasure二次，但是回调函数会回调很多次
        ViewTreeObserver viewTreeObserver = firstTxt.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int height = firstTxt.getHeight();
                int width = firstTxt.getWidth();
                Log.v("获取TextView宽高", "宽度:" + width + ",高度:" + height);
                return true;
            }
        });
        //-----------------------------------------------方法三
        //此方法会加载onMeasure二次，但是回调函数只回调一次
        ViewTreeObserver vto2 = firstTxt.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                firstTxt.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                firstTxt.append("\n\n"+firstTxt.getHeight()+","+firstTxt.getWidth());
            }
        });
// 获取屏幕宽高（方法1）
        String TAG = "SevenActivity";
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）
        Log.e(TAG, "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
// 获取屏幕密度（方法2）
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        Log.e(TAG, "xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.e(TAG, "density=" + density + "; densityDPI=" + densityDPI);
        screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
        screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）
        Log.e(TAG, "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
// 获取屏幕密度（方法3）
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        Log.e(TAG, "xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.e(TAG, "density=" + density + "; densityDPI=" + densityDPI);
        int screenWidthDip = dm.widthPixels; // 屏幕宽（dip，如：320dip）
        int screenHeightDip = dm.heightPixels; // 屏幕宽（dip，如：533dip）
        Log.e(TAG, "screenWidthDip=" + screenWidthDip + "; screenHeightDip=" + screenHeightDip);
        screenWidth = (int) (dm.widthPixels * density + 0.5f); // 屏幕宽（px，如：480px）
        screenHeight = (int) (dm.heightPixels * density + 0.5f); // 屏幕高（px，如：800px）
        Log.e(TAG, "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
    }


}
