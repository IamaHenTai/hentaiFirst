package com.example.ser01.demos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ser01 on 2017-02-05.
 *
 */

class ListViewAdapter extends BaseAdapter {

    private List<Map<String, String>> list = new ArrayList<>();
    private LayoutInflater mInflater;

    ListViewAdapter(Context context, List<Map<String, String>> list) {
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //如果缓存convertView为空，则需要创建View
        if(convertView == null)
        {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.lv_item, null);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            holder.text = (TextView) convertView.findViewById(R.id.tv_item1);
            holder.date = (TextView) convertView.findViewById(R.id.tv_item2);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.text.setText(list.get(position).get("input"));
        holder.date.setText(list.get(position).get("date"));
        return convertView;
    }

    //ViewHolder静态类
    private static class ViewHolder
    {
        TextView text;
        TextView date;
    }

}
