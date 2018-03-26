package com.example.administrator.okgotest.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Administrator on 2018/1/27.
 */

public class TestView extends View{

    Paint paint=new Paint();

    float currentx=40;
    float currenty=50;
    public TestView(Context context) {
        super(context);
    }
    //回调
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
    //检测组件大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawCircle(currentx,currenty,15,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentx=event.getX();
        currenty=event.getY();
            invalidate();
        return true;
    }
}
