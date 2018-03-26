package com.example.administrator.okgotest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.okgotest.JsonBean.ReturnMessage;
import com.example.administrator.okgotest.JsonBean.TestBean;
import com.example.administrator.okgotest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ReturnMessage  returnMessage=new ReturnMessage();

    public MyAdapter(Context context, ReturnMessage returnMessage) {
        this.context = context;
        this.returnMessage = returnMessage;
    }

    @Override
    public int getCount() {
        return returnMessage.getNumber();
    }

    @Override
    public Object getItem(int position) {
        return returnMessage.getContent().get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.list_item, null);
            holder.tv1 = convertView.findViewById(R.id.tv_name);
            holder.tv2 = convertView.findViewById(R.id.tv_word);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv1.setText(returnMessage.getContent().get(position).get("username"));
        holder.tv2.setText(returnMessage.getContent().get(position).get("loginid"));
        return convertView;

    }
    static class ViewHolder {
        TextView tv1;
        TextView tv2;
    }
}
