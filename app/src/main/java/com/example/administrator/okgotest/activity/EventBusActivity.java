package com.example.administrator.okgotest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.administrator.okgotest.JsonBean.CityInfo;
import com.example.administrator.okgotest.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        textView=(TextView)findViewById(R.id.textView);

        EventBus.getDefault().post(new CityInfo("北京"));
        EventBus.getDefault().register(this);

    }
    @Subscribe(sticky = true ,threadMode = ThreadMode.MAIN)
    public void onEvent(CityInfo cityInfo){
        textView.setText(cityInfo.getName());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
    }
}
