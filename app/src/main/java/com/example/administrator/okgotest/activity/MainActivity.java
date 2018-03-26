package com.example.administrator.okgotest.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.okgotest.JsonBean.FamilyInfo;
import com.example.administrator.okgotest.JsonBean.OrderChildInfo;
import com.example.administrator.okgotest.JsonBean.OrderInfo;
import com.example.administrator.okgotest.JsonBean.PostLoginAndRegisterUser;
import com.example.administrator.okgotest.JsonBean.ReturnMessage;
import com.example.administrator.okgotest.JsonBean.TestBean;
import com.example.administrator.okgotest.R;
import com.example.administrator.okgotest.adapter.MyAdapter;
import com.example.administrator.okgotest.adapter.TestView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private ListView listview;
    private List<TestBean.ContentBean> testBeanList = new ArrayList<>();
    private TestBean.ContentBean contentBean;
    private MyAdapter adapter;
    AlertDialog alertDialog;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listview);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.line1);
        TestView testView = new TestView(this);
        testView.setMinimumWidth(300);
        testView.setMinimumHeight(500);
        linearLayout.addView(testView);

        timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                Log.i("run: ","111");
//                timer.cancel();
            }
        }, 2000);
//        hospital();
//        login();
//        getPlanInfo();
//        getuserdata();
//        getorbit();
//        getfamilyinfobymobile();
//        addfamilyinfo();
//        allorder();
//        updatePassword();
        Observable.interval(2, TimeUnit.SECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return aLong.toString();
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String o) {
                        Log.i("onNext: ","hahahah"+o);
                    }
                });
    }


    private void allorder() {
        OkGo.<String>get(Urls.URL_ALLORDER)
                .tag(this)
                .params("userid", "f1a06db9580f42aeaaa0765b38940f74")
                .params("token", "2538a8ee2f9249d7ac3b36d90080813a")
                .cacheKey("allorder")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        if (returnMessage.getCode().equals("107")) {
                            Log.i("onSuccess: ", returnMessage.getMessage());
                        }
                        if (returnMessage.getCode().equals("108")) {
                            for (int i = 0; i < returnMessage.getNumber(); i++) {
                                OrderInfo orderInfo = JSON.parseObject(returnMessage.getContent().get(i).toString(), OrderInfo.class);
                                Log.i("onSuccess: ", orderInfo.getOrderbody());
                            }
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    private void addfamilyinfo() {
        FamilyInfo familyInfo = new FamilyInfo();
        familyInfo.setAge("2");
        familyInfo.setHeight("2");
        familyInfo.setIdentityCard("2");
        familyInfo.setMobile("2");
        familyInfo.setRealname("2");
        familyInfo.setWeight("2");
        familyInfo.setSex("1");
        String str = JSON.toJSONString(familyInfo);


//        + "?token=52dc24f288d44ca09170a5038d92b3de&loginid=83740a28b8d54f4889c0f3087795109d"
        OkGo.<String>post(Urls.URL_ADDFAMILTINFO)
                .tag(this)
                .params("token", "2538a8ee2f9249d7ac3b36d90080813a")
                .params("loginid", "f1a06db9580f42aeaaa0765b38940f74")
                .isSpliceUrl(true)
                .upJson(str)
                .cacheKey("addfamilyinfo")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("提示").setMessage("是否请求数据？").setCancelable(true);
                        alertDialog = builder.create();
                        alertDialog.show();

                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        if (returnMessage.getCode().equals("108")) {
                            Log.i("onSuccess: ", returnMessage.getContent().get(0).get("weight"));
                        }
                        if (returnMessage.getCode().equals("101")) {
                            Log.i("onSuccess: ", "服务器已存在此手机号");
                        }

                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        Log.i("onSuccess: ", returnMessage.getContent().get(0).get("weight"));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        alertDialog.dismiss();
                    }
                });
    }

    private void getfamilyinfobymobile() {
        OkGo.<String>get(Urls.URL_GETFAMILYINFOBYMOBILE + "bde24693d9a541f68a32b8f4ffa855d6")
                .tag(this)
                .cacheKey("getfamilyinfobymobile")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        for (int i = 0; i < returnMessage.getNumber(); i++) {
                            Log.i("onSuccess: ", returnMessage.getContent().get(i).get("realname"));
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        for (int i = 0; i < returnMessage.getNumber(); i++) {
                            Log.i("onSuccess: ", returnMessage.getContent().get(i).get("realname"));
                        }
                    }
                });
    }

    private void updatePassword() {
        OkGo.<String>get(Urls.updatepassword)
                .tag(this)
                .params("password", "111111")
                .params("username", "18201335822")
                .cacheKey("updatepassword")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        Log.i("onSuccess: ", returnMessage.getMessage().toString());
                    }
                });
    }


    private void getorbit() {
        OkGo.<String>get(Urls.URL_GETORBIT)
                .tag(this)
                .params("token", "bde24693d9a541f68a32b8f4ffa855d6")
                .params("userid", "adad47231b97494eb586788639671317")
                .params("orditdate", "2018-01-25")
                .cacheKey("getorbit")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        for (int i = 0; i < returnMessage.getNumber(); i++) {
                            Log.i("onSuccess: ", returnMessage.getContent().get(i).get("latitude"));
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        for (int i = 0; i < returnMessage.getNumber(); i++) {
                            Log.i("onSuccess123: ", returnMessage.getContent().get(i).get("latitude"));
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }


    private void hospital() {
        OkGo.<String>get(Urls.URL_HOSPITAL)//
                .tag(this)
                .params("token", "a0002f0344c245b5b12133753f8ec2bf")//传入请求参数
                .cacheKey("cachekey")//作为缓存的key
                .cacheMode(CacheMode.NO_CACHE)//设置缓存模式

                //StringCallback只返回成功
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("onSuccess: ", response.body());
                        ReturnMessage returnMessage = JSON.parseObject(
                                response.body().toString(), ReturnMessage.class);
                    }

                    @Override//适配器模式,可以不实现该方法
                    public void onError(Response<String> response) {

                    }
                });
    }

    private void login() {
        PostLoginAndRegisterUser userinfo = new PostLoginAndRegisterUser();
        userinfo.setPassword("121212");
        userinfo.setUsername("18201335822");
        String jsonStr = JSON.toJSONString(userinfo);
//        无缓存模式 CacheMode.NO_CACHE
//        默认缓存模式,遵循304头 CacheMode.DEFAULT
//        请求网络失败后读取缓存 CacheMode.REQUEST_FAILED_READ_CACHE
//        如果缓存不存在才请求网络，否则使用缓存 CacheMode.IF_NONE_CACHE_REQUEST
//        先使用缓存，不管是否存在，仍然请求网络 CacheMode.FIRST_CACHE_THEN_REQUEST

        OkGo.<String>post(Urls.URL_USERLOGIN)
                .tag(this)
                .upJson(jsonStr)
                .cacheKey("logininfo")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Log.i("onSuccess: ", response.body());
                        ReturnMessage returnMessage = JSON.parseObject(
                                response.body().toString(), ReturnMessage.class);
                        adapter = new MyAdapter(MainActivity.this, returnMessage);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("onError: ", "onerror");
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        Log.i("onCacheSuccess: ", "onCacheSuccess");
                        ReturnMessage returnMessage = JSON.parseObject(
                                response.body().toString(), ReturnMessage.class);
                        adapter = new MyAdapter(MainActivity.this, returnMessage);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        Log.i("onFinish: ", "onfinish");
                        listview.setAdapter(adapter);
                    }
                });
final String ss[]={"a","b","c","d"};
        /***
         * flatmap数据类型转换 1对多
         */
        Observable.just(2,1,3,4,5,6,7,8)
                .flatMap(new Func1<Integer, Observable<? extends String[]> >() {
                    @Override
                    public Observable<? extends  String[]> call(Integer integer) {
                        return Observable.just(ss);
                    }
                }).subscribe(new Subscriber<String[]>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String[] s) {
                Log.i( "123onNext: ",s.toString());
            }
        });

    }

    private void getPlanInfo() {
        OkGo.<String>get(Urls.URL_GETPLANINFO)
                .tag(this)
                .params("token", "bde24693d9a541f68a32b8f4ffa855d6")
                .cacheKey("getplaninfo")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        String childlist = returnMessage.getContent().get(0).get("orderPlanChildList").toString();
                        Log.i("onSuccess: ", childlist);
                        List<OrderChildInfo> orderChildInfo = JSON.parseArray(childlist, OrderChildInfo.class);
                        for (int i = 0; i < orderChildInfo.size(); i++) {
                            Log.i("onSuccess: ", orderChildInfo.get(i).getOrdername() + "-" + orderChildInfo.get(i).getOrderunitprice() + "-" + orderChildInfo.get(i).getOrderunitname());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("onError: ", "onerror");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void getuserdata() {
        OkGo.<String>get(Urls.URL_GETUSERDATA)
                .tag(this)
                .params("familyid", "d453992e74164af6ab5133cc63c23e80")
                .params("token", "3959b9cc8f08495c8c26a766bcccf271")
                .cacheKey("getuserdata")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ReturnMessage returnMessage = JSON.parseObject(response.body().toString(), ReturnMessage.class);
                        for (int i = 0; i < returnMessage.getNumber(); i++) {
                            String measuretime = returnMessage.getContent().get(i).get("measureTime");
                            Log.i("onSuccess: ", measuretime);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);

    }
}
