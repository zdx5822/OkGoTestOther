package com.example.administrator.okgotest.MVC_model.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.okgotest.JsonBean.CityInfo;
import com.example.administrator.okgotest.R;
import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class CityAdapter extends BaseAdapter {
    private List<CityInfo> cityInfoList;
    private Context context;

    public CityAdapter(Context context, List<CityInfo> list) {
        cityInfoList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cityInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView == null) {
            convertView = View.inflate((context), R.layout.list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String word = cityInfoList.get(position).getHeaderWord();
        holder.tv_word.setText(word);
        holder.tv_name.setText(cityInfoList.get(position).getName());
        //将相同字母开头的合并在一起
        if (position == 0) {
            //第一个是一定显示的
            holder.tv_word.setVisibility(View.VISIBLE);
        } else {
            //后一个与前一个对比,判断首字母是否相同，相同则隐藏
            String headerWord = cityInfoList.get(position - 1).getHeaderWord();
            if (word.equals(headerWord)) {
                holder.tv_word.setVisibility(View.GONE);
            } else {
                holder.tv_word.setVisibility(View.VISIBLE);
            }
        }

        return convertView;

    }

    class ViewHolder {
        TextView tv_word;
        TextView tv_name;

        public ViewHolder(View view) {
            tv_word = view.findViewById(R.id.tv_word);
            tv_name = view.findViewById(R.id.tv_name);
        }
    }

}
