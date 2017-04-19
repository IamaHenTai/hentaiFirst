package com.example.ser01.demos.view;

import android.content.Context;
import android.widget.GridView;

/**
 * Created by Ser01 on 2017-01-10.
 *      自定义gridview  取消gridview的自动折叠功能
 */

public class MyGridView extends GridView{
    public MyGridView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
