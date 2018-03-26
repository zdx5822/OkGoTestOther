package com.example.administrator.okgotest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.okgotest.JsonBean.CityInfo;
import com.example.administrator.okgotest.JsonBean.ReturnMessage;
import com.example.administrator.okgotest.R;
import com.example.administrator.okgotest.adapter.CityChooseView;
import com.jakewharton.rxbinding.view.RxView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class CityActivity extends AppCompatActivity {
    private ListView listview;
    private CityChooseView word;
    private List<CityInfo> list;
    private Handler handler;
    private TextView tv;
    private Button btn;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        listview = (ListView) findViewById(R.id.listview);
        word = (CityChooseView) findViewById(R.id.words);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        EventBus.getDefault().register(this);

//        timer = new Timer();
//        timer.schedule(new TimerTask(){
//            public void run(){
//                Log.i("run: ","111");
////                timer.cancel();
//            }
//        }, 2000);
        Observable.interval(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.i("call: ",aLong.toString());
                    }
                });




        RxView.clicks(btn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //粘性事件
                        EventBus.getDefault().postSticky(new CityInfo("Hello everyone!"));
                        Intent intent=new Intent(CityActivity.this,EventBusActivity.class);
                        startActivity(intent);

                    }
                });

        hospital();
        //设置列表点击滑动监听
        handler = new Handler();
        word.setOnWordsChangeListener(new CityChooseView.onWordsChangeListener() {
            @Override
            public void wordsChange(String words) {
                updateWord(words);
                updateListView(words);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityInfo cityInfo = (CityInfo) parent.getItemAtPosition(position);
                Log.i("onItemClick: ", cityInfo.getName());
            }
        });

    }

    /***
     * 主线程发送事件
     * @param cityInfo
     */

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userEventBus(CityInfo cityInfo) {
        btn.setText(cityInfo.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 更新中央的字母提示
     *
     * @param words 首字母
     */
    private void updateWord(String words) {
        tv.setText(words);
        tv.setVisibility(View.VISIBLE);
        //清空之前的所有消息
        handler.removeCallbacksAndMessages(null);
        //500ms后让tv隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setVisibility(View.GONE);
            }
        }, 500);
    }

    private void hospital() {
        OkGo.<String>get(Urls.URL_HOSPITAL)//
                .tag(this)
                .params("token", "3959b9cc8f08495c8c26a766bcccf271")//传入请求参数
                .cacheKey("cachekey")//作为缓存的key
                .cacheMode(CacheMode.NO_CACHE)//设置缓存模式

                //StringCallback只返回成功
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("onSuccess: ", response.body());
                        ReturnMessage returnMessage = JSON.parseObject(
                                response.body().toString(), ReturnMessage.class);
                        list = new ArrayList<>();
                        for (int i = 0; i < returnMessage.getNumber(); i++) {
                            Log.i("onSuccess: ", returnMessage.getContent().get(i).get("hospitalName").toString());
                            String hospital = returnMessage.getContent().get(i).get("areaName").toString() + "    " + returnMessage.getContent().get(i).get("hospitalName").toString();
                            CityInfo cityInfo = new CityInfo(hospital);
                            list.add(cityInfo);
                        }
                        //对集合排序
                        Collections.sort(list, new Comparator<CityInfo>() {
                            @Override
                            public int compare(CityInfo lhs, CityInfo rhs) {
                                //根据拼音进行排序
                                return lhs.getPinyin().compareTo(rhs.getPinyin());
                            }
                        });

                        MyAdapter myAdapter = new MyAdapter(CityActivity.this, list);
                        listview.setAdapter(myAdapter);
                        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(AbsListView view, int scrollState) {

                            }

                            @Override
                            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                word.setTouchIndex(list.get(firstVisibleItem).getHeaderWord());
                            }
                        });
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                CityInfo cityInfo = (CityInfo) parent.getAdapter().getItem(position);
                                Toast.makeText(CityActivity.this, cityInfo.getName(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override//适配器模式,可以不实现该方法
                    public void onError(Response<String> response) {

                    }
                });
    }


    public class MyAdapter extends BaseAdapter {
        private List<CityInfo> list;
        private LayoutInflater inflater;

        public MyAdapter(Context context, List<CityInfo> list) {
            inflater = LayoutInflater.from(context);
            this.list = list;
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
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.list_item, null);
                holder.tv_word = (TextView) convertView.findViewById(R.id.tv_word);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String word = list.get(position).getHeaderWord();
            holder.tv_word.setText(word);
            holder.tv_name.setText(list.get(position).getName());
            //将相同字母开头的合并在一起
            if (position == 0) {
                //第一个是一定显示的
                holder.tv_word.setVisibility(View.VISIBLE);
            } else {
                //后一个与前一个对比,判断首字母是否相同，相同则隐藏
                String headerWord = list.get(position - 1).getHeaderWord();
                if (word.equals(headerWord)) {
                    holder.tv_word.setVisibility(View.GONE);
                } else {
                    holder.tv_word.setVisibility(View.VISIBLE);
                }
            }
            return convertView;
        }

        private class ViewHolder {
            private TextView tv_word;
            private TextView tv_name;
        }
    }

    /**
     * @param words 首字母
     */
    private void updateListView(String words) {
        for (int i = 0; i < list.size(); i++) {
            String headerWord = list.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出来
            if (words.equals(headerWord)) {
                //将列表选中哪一个
                listview.setSelection(i);
                //找到开头的一个即可
                return;
            }
        }
    }
}
