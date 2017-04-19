package com.example.ser01.demos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.ser01.demos.fivedemo.FiveActivity;
import com.example.ser01.demos.fivedemo.PermissionUtils;
import com.example.ser01.demos.seconddemo.SecondActivity;
import com.example.ser01.demos.view.MyDatePickerDialog;
import com.example.ser01.demos.view.MyDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    private EditText et_input;
    private ListView lv;
    private ListViewAdapter adapter;
    private List<Map<String, String>> list_input = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private Button btn1,btn2;
    private int n;
    private ArrayAdapter<String> arr_adapter;
    private MyDialog dialog;
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        initView();
    }

    private void initView() {
        View view = this.getLayoutInflater().inflate(R.layout.dialog, null);
        et_input = (EditText) findViewById(R.id.et_input);
        lv = (ListView) findViewById(R.id.lv);
        Spinner sp = (Spinner) view.findViewById(R.id.sp);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> parent, View view, int position, long id) {
                n = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        et_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(!"".equals(et_input.getText().toString())){

                    if (event.getAction() == 1 && keyCode == 66){//
                        Map<String, String> map = new HashMap<>();
                        map.put("input", et_input.getText().toString());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                        map.put("date", formatter.format(curDate));
                        list_input.add(map);
                        adapter = new ListViewAdapter(MainActivity.this, list_input);
                        lv.setAdapter(adapter);
                        lv.setFocusable(false);
                        btn1.setFocusable(false);
                        btn2.setFocusable(false);
                        et_input.setText("");
                        et_input.requestFocus();
                        list.add(formatter.format(curDate));
                        //适配器
                        arr_adapter=
                                new ArrayAdapter<>(MainActivity.this,
                                        android.R.layout.simple_spinner_item, list);
                        //设置样式
                        arr_adapter.setDropDownViewResource(
                                android.R.layout.simple_spinner_dropdown_item);

                    }
                }

                return false;
            }
        });
//        dialog = (MyDialog) new MyDialog.Builder(MainActivity.this)
//                .setTitle("请选择日期")
//                .setView(view)
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //ignore
//                    }
//                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                for (int i = 0; i < list_input.size(); i++) {
//                    String date = list.get(n);
//                    String date2 = list_input.get(n).get("date");
//                    if (date.equals(date2)){
//                        List<Map<String, String>> listspinner = new ArrayList<>();
//                        Map<String, String> newMap = new HashMap<>();
//                        newMap.put("input", list_input.get(n).get("input"));
//                        newMap.put("date", date2);
//                        listspinner.add(newMap);
//                        adapter = new ListViewAdapter(MainActivity.this, listspinner);
//                        lv.setAdapter(adapter);
//                    }
//                }
//            }
//        })
//            .create();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                if (Build.VERSION.SDK_INT >= 23) {
                    PermissionUtils.requestPermission(
                            this, PermissionUtils.CODE_GET_ACCOUNTS, mPermissionGrant);
                }
                break;
            case R.id.btn2:
                Intent intent2 = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    private PermissionUtils.PermissionGrant mPermissionGrant =
            new PermissionUtils.PermissionGrant() {
                @Override
                public void onPermissionGranted(int requestCode) {
                    switch (requestCode) {
                        case PermissionUtils.CODE_GET_ACCOUNTS:
                            Uri uri = Uri.parse("content://contacts/people");
                            Intent intent = new Intent(Intent.ACTION_PICK, uri);
                            startActivityForResult(intent, 0);
                            break;
                        default:
                            break;
                    }
                }
            };

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(
                MainActivity.this, requestCode, permissions,
                grantResults, mPermissionGrant);
    }

    /*
   * 跳转联系人列表的回调函数
   * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if(data==null)
                {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri=data.getData();
                String[] contacts=getPhoneContacts(uri);
                if (contacts != null) {
                    et_input.setText(String.valueOf(contacts[0] + contacts[1]));
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private String[] getPhoneContacts(Uri uri){
        String[] contact=new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor=cr.query(uri,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0]=cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if(phone != null){
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        }
        else
        {
            return null;
        }
        return contact;
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
            et_input.setText(
                    new StringBuffer()
                            .append(mYear).append("-")
                            .append(mMonth + 1).append("-").append(mDay));
        }
    };

}
