package com.example.administrator.okgotest.MVP_model.login;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.okgotest.MVC_model.controller.CityActivity;
import com.example.administrator.okgotest.MVP_model.login.model.onLoginModelImpl;
import com.example.administrator.okgotest.MVP_model.login.presenter.onLoginPresenter;
import com.example.administrator.okgotest.MVP_model.login.presenter.onLoginPresenterImpl;
import com.example.administrator.okgotest.MVP_model.login.view.onLoginView;
import com.example.administrator.okgotest.R;

public class loginActivity extends Activity implements onLoginView, View.OnClickListener {
    private EditText username;
    private EditText password;
    private onLoginPresenter onLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        findViewById(R.id.btn_login).setOnClickListener(this);
        onLoginPresenter=new onLoginPresenterImpl(this,new onLoginModelImpl());
    }

    @Override
    public void setUsernameError() {
        username.setError("用户名不能为空");
    }

    @Override
    public void setPasswordError() {
        password.setError("密码不能为空");
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT);
        Intent intent=new Intent(this, CityActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onLoginPresenter.onDestory();

    }

    @Override
    public void onClick(View v) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        Log.i("onClick: ", user + "----" + pass);
        onLoginPresenter.validateUserInfo(user, pass);
    }


}
