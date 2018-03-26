package com.example.administrator.okgotest.MVC_model.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.okgotest.JsonBean.CityInfo;
import com.example.administrator.okgotest.JsonBean.ReturnMessage;
import com.example.administrator.okgotest.MVC_model.model.CityInfoListener;
import com.example.administrator.okgotest.MVC_model.model.onCityModel;
import com.example.administrator.okgotest.MVC_model.model.onCityModelImpl;
import com.example.administrator.okgotest.R;
import com.example.administrator.okgotest.activity.EventBusActivity;
import com.example.administrator.okgotest.adapter.CityChooseView;
import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class CityActivity extends AppCompatActivity implements CityInfoListener{
    private ListView listview;
    private CityChooseView word;
    private List<CityInfo> list;
    private Handler handler;
    private TextView tv;
    private Button btn;
    private Timer timer;
    private onCityModel onCityModel;
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
        onCityModel=new onCityModelImpl();
        onCityModel.getCityModel("3959b9cc8f08495c8c26a766bcccf271",this);

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

    @Override
    public void onSuccess(ReturnMessage returnMessage) {
        list = new ArrayList<>();
        for (int i = 0; i < returnMessage.getNumber(); i++) {
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

        CityAdapter myAdapter = new CityAdapter(CityActivity.this, list);
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

    @Override
    public void onError() {
        Toast.makeText(CityActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFirst() {
        Toast.makeText(CityActivity.this, "开始请求", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish() {
        Toast.makeText(CityActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCacheSuccess(ReturnMessage returnMessage) {
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

        CityAdapter myAdapter = new CityAdapter(CityActivity.this, list);
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
}
