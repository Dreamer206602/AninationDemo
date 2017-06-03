package com.booboomx.aninationdemo.Ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.booboomx.aninationdemo.R;
import com.booboomx.aninationdemo.widget.BubbingLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RubbingActivity extends AppCompatActivity {

    @Bind(R.id.rubbingLayout)
    BubbingLayout mRubbingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbing);
        ButterKnife.bind(this);






    }


    @OnClick(R.id.rubbingLayout)
    public void onClick(){
        mRubbingLayout.setHeart();
    }
}
