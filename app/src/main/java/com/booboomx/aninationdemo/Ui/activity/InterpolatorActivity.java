package com.booboomx.aninationdemo.Ui.activity;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.booboomx.aninationdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InterpolatorActivity extends AppCompatActivity {

    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.rootView)
    RelativeLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        ButterKnife.bind(this);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        init();
    }

    private void init() {

        int width = mRootView.getWidth();
        int height = mRootView.getHeight();


        Interpolator ad=new AccelerateDecelerateInterpolator();

        Interpolator dce=new DecelerateInterpolator();

        Interpolator ac=new AccelerateInterpolator();

        Interpolator interpolator=new LinearInterpolator();


        PointF pointF1=new PointF(width/2,0);
        PointF pointF2=new PointF(width/2-mImageView.getWidth(),height-mImageView.getHeight());

        ValueAnimator animator=new ValueAnimator();
        animator.setInterpolator(dce);
        animator.setObjectValues(pointF1,pointF2);
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {

                PointF pointF1= (PointF) startValue;
                PointF pointF2= (PointF) endValue;


                float x = pointF1.x + fraction * (pointF2.x - pointF1.x);
                float y = pointF1.y + fraction * (pointF2.y - pointF1.y);


                PointF pointF=new PointF(x,y);


                return pointF;
            }
        });


        animator.setDuration(4000);
        animator.start();


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                PointF pointF = (PointF) animation.getAnimatedValue();

                mImageView.setX(pointF.x);
                mImageView.setY(pointF.y);

            }
        });








    }
}
