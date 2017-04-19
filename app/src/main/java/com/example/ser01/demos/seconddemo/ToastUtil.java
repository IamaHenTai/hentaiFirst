package com.example.ser01.demos.seconddemo;

import android.view.Gravity;
import android.widget.Toast;



public class ToastUtil {

	public static void show(Object obj) {
		if(obj != null){
			Toast toast = Toast.makeText(BaseActivity.getInstance(), obj.toString(),Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}else{
			Toast toast = Toast.makeText(BaseActivity.getInstance(), "null 空字符",Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}

}
