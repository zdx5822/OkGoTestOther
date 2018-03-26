package com.example.administrator.okgotest.MVP_model.login.model;

import com.example.administrator.okgotest.MVP_model.login.entity.UserInfo;

/**
 * Created by Administrator on 2018/3/23.
 */

public interface onLoginModel {
    void login(UserInfo userInfo, final onLoginListener onLoginListener);
}
