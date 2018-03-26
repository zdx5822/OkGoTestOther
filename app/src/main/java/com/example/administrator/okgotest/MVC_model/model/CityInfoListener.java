package com.example.administrator.okgotest.MVC_model.model;

import com.example.administrator.okgotest.JsonBean.ReturnMessage;

/**
 * Created by Administrator on 2018/3/23.
 */

public interface CityInfoListener {
    void onSuccess(ReturnMessage returnMessage);
    void onError();
    void onFirst();
    void onFinish();
    void  onCacheSuccess(ReturnMessage returnMessage);
}
