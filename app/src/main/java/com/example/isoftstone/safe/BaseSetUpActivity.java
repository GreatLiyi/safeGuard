package com.example.isoftstone.safe;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by isoftstone on 15/10/30.
 */
public abstract class BaseSetUpActivity extends Activity{
    public GestureDetector detector;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


                //向左滑动
                if ((e1.getRawX()-e2.getRawX())>200){

                    showNextPage();
                    return true;
                }
                //向右话
                if ((e2.getRawX()-e1.getRawX())>200){
                    showPreviosPage();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

        });
    }
    public abstract void showNextPage();
    public abstract void showPreviosPage();
    public void next(View view){
        showNextPage();
    }
    public void previous(View view){
        showPreviosPage();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
