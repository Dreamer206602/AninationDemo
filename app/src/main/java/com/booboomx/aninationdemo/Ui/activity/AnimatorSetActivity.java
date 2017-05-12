package com.booboomx.aninationdemo.Ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.booboomx.aninationdemo.R;
import com.booboomx.aninationdemo.typeEvaluator.BounceEaseOut;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatorSetActivity extends AppCompatActivity {


    @Bind(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn)
    public void  OnClick(){
        setDrout();
    }

    private void setDrout() {


        int top = mText.getHeight() + mText.getTop();


        AnimatorSet animatorSet=new AnimatorSet();

        ObjectAnimator alpha=ObjectAnimator.ofFloat(mText,"alpha",0,1);


        BounceEaseOut bounceEaseOut=new BounceEaseOut(1000);
        ValueAnimator translation=ObjectAnimator.ofFloat(mText,"translationY",-top,0);
        translation.setEvaluator(bounceEaseOut);

        animatorSet.playTogether(alpha,translation);
        animatorSet.setDuration(1600);
        animatorSet.start();
    }


}
