package com.example.isoftstone.safe;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by isoftstone on 15/10/30.
 */
public class settingActivity extends RelativeLayout{


    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android/com.example.isoftstone.safe";
    private TextView tvTitle;
    private TextView tvDesc;
    private CheckBox cbStatus;
    private String mTitle;
    private String mDescOn;
    private String mdescOff;

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        return super.addViewInLayout(child, index, params);
    }

    public settingActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTitle = attrs.getAttributeValue(NAMESPACE,"title");
        mDescOn = attrs.getAttributeValue(NAMESPACE,"desc_on");
        mdescOff = attrs.getAttributeValue(NAMESPACE,"desc_off");
        initView();
    }

    public settingActivity(Context context) {
        super(context);
        initView();
    }

    public settingActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View.inflate(getContext(),R.layout.view_setting_item,this);
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvDesc = (TextView)findViewById(R.id.tv_desc);
        cbStatus =  (CheckBox)findViewById(R.id.cb_status);
        tvTitle.setText(mTitle);

    }
    public void setDesc(String title){
        tvTitle.setText(title);
    }

    public boolean isChecked(){
        return cbStatus.isChecked();
    }
    public void setChecked(boolean check){
        cbStatus.setChecked(check);
        if (check){
            setDesc(mDescOn);
        }else {
            setDesc(mdescOff);
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public settingActivity(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
