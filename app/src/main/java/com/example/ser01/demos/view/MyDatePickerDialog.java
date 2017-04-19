package com.example.ser01.demos.view;

import android.app.DatePickerDialog;
import android.content.Context;

/**
 * Created by Ser01 on 2017-02-28.
 */

public class MyDatePickerDialog extends DatePickerDialog {

    public MyDatePickerDialog(
            Context context,
            int themeResId,
            OnDateSetListener listener,
            int year,
            int monthOfYear,
            int dayOfMonth) {
        super(context, themeResId, listener, year, monthOfYear, dayOfMonth);
    }
}
