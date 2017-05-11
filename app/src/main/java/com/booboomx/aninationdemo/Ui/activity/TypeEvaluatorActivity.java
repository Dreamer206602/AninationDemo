package com.booboomx.aninationdemo.Ui.activity;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.booboomx.aninationdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TypeEvaluatorActivity extends AppCompatActivity {

    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.rootView)
    RelativeLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_evaluator);
        ButterKnife.bind(this);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            init();
        }
    }

    private void init() {

        int width = mRootView.getWidth();
        int height = mRootView.getHeight();
        PointF pointF1=new PointF(width/2,0);
        PointF pointF2=new PointF(width-mImageView.getWidth(),height-mImageView.getHeight());

        ValueAnimator animator=new ValueAnimator();
        animator.setObjectValues(pointF1,pointF2);
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {

               PointF startPoint= (PointF) startValue;
               PointF endPoint= (PointF) endValue;

                float x = startPoint.x + fraction * (endPoint.x - startPoint.x);
                float y = startPoint.y + fraction * (endPoint.y - startPoint.y);

                PointF pointF=new PointF(x,y);
                Log.d("TypeEvaluator","fraction=" + fraction +
                        "\n startPoint.x = "+ startPoint.x +"startPoint.y = " + startPoint.y
                        +"\nx = " + x+ "__y = "+ y);


                return pointF;
            }
        });

//        animator.setEvaluator(new FloatEvaluator());

        animator.setDuration(4000);
        animator.start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                PointF pointF= (PointF) animation.getAnimatedValue();
                mImageView.setX(pointF.x);
                mImageView.setY(pointF.y);

            }
        });


    }
}
