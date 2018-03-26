package com.example.administrator.okgotest.MVP_model.login.presenter;

import android.text.TextUtils;

import com.example.administrator.okgotest.MVP_model.login.entity.UserInfo;
import com.example.administrator.okgotest.MVP_model.login.model.onLoginListener;
import com.example.administrator.okgotest.MVP_model.login.model.onLoginModel;
import com.example.administrator.okgotest.MVP_model.login.view.onLoginView;

/**
 * Created by Administrator on 2018/3/23.
 */

public class onLoginPresenterImpl implements onLoginPresenter,onLoginListener {
    private onLoginView onLoginView;
    private onLoginModel onLoginModel;

    public onLoginPresenterImpl(com.example.administrator.okgotest.MVP_model.login.view.onLoginView onLoginView, com.example.administrator.okgotest.MVP_model.login.model.onLoginModel onLoginModel) {
        this.onLoginView = onLoginView;
        this.onLoginModel = onLoginModel;
    }

    @Override
    public void validateUserInfo(String username, String password) {
        if(TextUtils.isEmpty(username)){
            onLoginView.setUsernameError();
            return;
        }
        if(TextUtils.isEmpty(password)){
            onLoginView.setPasswordError();
            return;
        }
        UserInfo userInfo=new UserInfo(username,password);
        onLoginModel.login(userInfo,this);
    }

    @Override
    public void onDestory() {
        onLoginView=null;
    }

    @Override
    public void onSuccess() {
        if(onLoginView!=null)
            onLoginView.onLoginSuccess();
    }

    @Override
    public void onError() {
        if (onLoginView!=null)
            onLoginView.onError();
    }

}
