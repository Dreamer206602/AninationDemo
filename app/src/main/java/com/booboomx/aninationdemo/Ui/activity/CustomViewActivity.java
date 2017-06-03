package com.booboomx.aninationdemo.Ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.booboomx.aninationdemo.R;
import com.booboomx.aninationdemo.widget.RadarView;

/**
 * 自定义View
 */
public class CustomViewActivity extends AppCompatActivity {

    private RadarView mRadarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        mRadarView= (RadarView) findViewById(R.id.radarView);

        mRadarView.setMainPaintColor(getResources().getColor(R.color.colorAccent));
        mRadarView.setValuePaintColor(getResources().getColor(R.color.colorPrimaryDark));
    }
}
