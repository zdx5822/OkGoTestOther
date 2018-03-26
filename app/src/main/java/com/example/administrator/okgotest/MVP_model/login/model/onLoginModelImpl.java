package com.example.administrator.okgotest.MVP_model.login.model;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.okgotest.MVP_model.login.entity.UserInfo;
import com.example.administrator.okgotest.MVP_model.login.view.onLoginView;
import com.example.administrator.okgotest.activity.Urls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by Administrator on 2018/3/23.
 */

public class onLoginModelImpl implements onLoginModel{

    @Override
    public void login(UserInfo userInfo, final onLoginListener onLoginListener) {
        String jsonStr = JSON.toJSONString(userInfo);
        OkGo.<String>post(Urls.URL_USERLOGIN)
                .tag(this)
                .upJson(jsonStr)
                .cacheKey("logininfo")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        onLoginListener.onSuccess();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        onLoginListener.onError();
                    }
                });
    }
}
