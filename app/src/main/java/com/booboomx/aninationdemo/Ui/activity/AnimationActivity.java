package com.booboomx.aninationdemo.Ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.booboomx.aninationdemo.R;
import com.booboomx.aninationdemo.Utils.JumpUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.booboomx.aninationdemo.R.id.fab;

public class AnimationActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @OnClick({R.id.btn_interpolator_ball, R.id.btn_type_evaluator_ball, R.id.btn_rotation_ball,R.id.btn_Dropout,R.id.btn_bezier})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_type_evaluator_ball:
                JumpUtils.go2TypeEvaluatorActivity(this);
                break;

            case R.id.btn_interpolator_ball:
                JumpUtils.go2InterpolatorActivity(this);
                break;
            case R.id.btn_Dropout:
                JumpUtils.go2AnimatorSetActivity(this);
                break;
            case R.id.btn_rotation_ball:
                JumpUtils.go2RotationActivity(this);
                break;
            case R.id.btn_bezier:
                JumpUtils.go2BezierActivity(this);
                break;


        }
    }


    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
