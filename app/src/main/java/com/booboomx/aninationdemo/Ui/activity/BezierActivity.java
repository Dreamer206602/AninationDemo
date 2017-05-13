package com.booboomx.aninationdemo.Ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.booboomx.aninationdemo.R;
import com.booboomx.aninationdemo.Utils.JumpUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BezierActivity extends AppCompatActivity {

    @Bind(R.id.btn_shopping)
    AppCompatButton mBtnShopping;
    @Bind(R.id.btn_rubbing)
    AppCompatButton mBtnRubbing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_shopping, R.id.btn_rubbing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_shopping:
                JumpUtils.go2ShoppingActivity(this);
                break;
            case R.id.btn_rubbing:
                JumpUtils.go2RubbingLayoutActivity(this);
                break;
        }
    }
}
