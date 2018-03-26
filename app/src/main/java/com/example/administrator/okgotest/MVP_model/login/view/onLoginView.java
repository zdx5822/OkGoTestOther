package com.example.administrator.okgotest.MVP_model.login.view;

/**
 * Created by Administrator on 2018/3/23.
 */

public interface onLoginView {
    void setUsernameError();
    void setPasswordError();
    void onLoginSuccess();
    void onError();
}
