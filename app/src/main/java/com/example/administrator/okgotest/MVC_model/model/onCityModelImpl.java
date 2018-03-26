package com.example.administrator.okgotest.MVC_model.model;

import com.alibaba.fastjson.JSON;
import com.example.administrator.okgotest.JsonBean.ReturnMessage;
import com.example.administrator.okgotest.activity.Urls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * Created by Administrator on 2018/3/23.
 */

public class onCityModelImpl implements onCityModel{

    @Override
    public void getCityModel(String token,final CityInfoListener cityInfoListener) {
        OkGo.<String>get(Urls.URL_HOSPITAL)//
                .tag(this)
                .params("token", token)//传入请求参数
                .cacheKey("cachekey")//作为缓存的key
                .cacheMode(CacheMode.NO_CACHE)//设置缓存模式
                //StringCallback只返回成功
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ReturnMessage returnMessage = JSON.parseObject(
                                response.body().toString(), ReturnMessage.class);
                        cityInfoListener.onSuccess(returnMessage);
                    }

                    @Override//适配器模式,可以不实现该方法
                    public void onError(Response<String> response) {
                        cityInfoListener.onError();
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        cityInfoListener.onFirst();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        cityInfoListener.onFinish();
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        ReturnMessage returnMessage = JSON.parseObject(
                                response.body().toString(), ReturnMessage.class);
                        cityInfoListener.onCacheSuccess(returnMessage);
                    }
                });
    }
}
