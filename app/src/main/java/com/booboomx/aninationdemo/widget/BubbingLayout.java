package com.booboomx.aninationdemo.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.booboomx.aninationdemo.R;
import com.booboomx.aninationdemo.typeEvaluator.HeartBezierEvaluator;

import java.util.Random;

/**
 * Created by booboomx on 17/5/12.
 */

public class BubbingLayout extends RelativeLayout {
    private Interpolator line = new LinearInterpolator();//线性
    private Interpolator acc = new AccelerateInterpolator();//加速
    private Interpolator dce = new DecelerateInterpolator();//减速
    private Interpolator accdec = new AccelerateDecelerateInterpolator();//先加速后减速
    private Interpolator[] interpolators;

    private int mHeight;
    private int mWidth;
    private LayoutParams lp;
    private Drawable[] drawables;
    private Random random = new Random();

    private int dHeight;
    private int dWidth;


    public BubbingLayout(Context context) {
        super(context);
        init();
    }


    public BubbingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public BubbingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        //初始化小球
        drawables = new Drawable[3];
        Drawable red = getResources().getDrawable(R.drawable.red_ball);
        Drawable yellow = getResources().getDrawable(R.drawable.yellow_ball);
        Drawable blue = getResources().getDrawable(R.drawable.blue_ball);



        drawables[0]=red;
        drawables[1]=yellow;
        drawables[2]=blue;

        //获取图的宽高 用于后面的计算
        //注意 这里由于图片的大小都是一样的,所以只取了一个

        int dHeight = red.getIntrinsicHeight();
        int dWidth = red.getIntrinsicWidth();


        lp=new LayoutParams(dWidth,dHeight);
        lp.addRule(CENTER_HORIZONTAL,TRUE);
        lp.addRule(ALIGN_PARENT_BOTTOM,TRUE);


        //初始化插值器
        interpolators=new Interpolator[4];
        interpolators[0]=line;
        interpolators[1]=acc;
        interpolators[2]=dce;
        interpolators[3]=accdec;




    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth=getMeasuredWidth();
        mHeight=getMeasuredHeight();

    }



    public void  setHeart(){

        ImageView imageView=new ImageView(getContext());


        // 随机一个小球
        imageView.setImageDrawable(drawables[random.nextInt(3)]);
        imageView.setLayoutParams(lp);

        addView(imageView);

        Animator set=getAnimator(imageView);
        set.addListener(new AnimEndListener(imageView));
        set.start();
    }

    private Animator getAnimator(View view) {


        AnimatorSet set=getEnterAnimator(view);


        ValueAnimator bezierValueAnimator=getBezierValueAnimator(view);

        AnimatorSet finalSet=new AnimatorSet();
        finalSet.playSequentially(set);
        finalSet.playSequentially(set,bezierValueAnimator);

        finalSet.setInterpolator(interpolators[random.nextInt(4)]);

        finalSet.setTarget(view);
        return finalSet;
    }

    /**
     * 开始动画  先由暗－>明 中心放大
     *
     * @param view
     * @return
     */
    private AnimatorSet getEnterAnimator(View view) {


        ObjectAnimator alpha=ObjectAnimator.ofFloat(view,View.ALPHA,0.2f,1.0f);
        ObjectAnimator scaleX=ObjectAnimator.ofFloat(view,View.SCALE_X,0.2f,1.0f);
        ObjectAnimator ScaleY=ObjectAnimator.ofFloat(view,View.SCALE_Y,0.2f,1.0f);



        AnimatorSet set=new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new LinearInterpolator());
        set.playTogether(alpha,scaleX,ScaleY);
        set.setTarget(view);
        return set;


    }



    private ValueAnimator getBezierValueAnimator(View target){
        //贝塞尔计算器 传入三阶公式的必要参数 （ 由于起点和终点已确定，这里控制曲线则由中间2个点来控制 ）

        HeartBezierEvaluator evaluator=new HeartBezierEvaluator(getPointF(2),getPointF(1));



        PointF startPoint=new PointF((mWidth-dWidth)/2,
                mHeight-dHeight);


        PointF endPointF=new PointF(random.nextInt(getWidth()),0);

        // 第三个参数是起点 第四个参数是终点

        ValueAnimator animator=ValueAnimator.ofObject(evaluator,startPoint,endPointF);

        animator.addUpdateListener(new BezierListener(target));
        animator.setTarget(target);
        animator.setDuration(3000);
        return animator;


    }

    /**
     * 获取中间的两个 点
     * @param scale
     * @return
     */
    private PointF getPointF(int scale){

        PointF pointF=new PointF();
        pointF.x=random.nextInt((mWidth-100));//减去100 是为了控制 x轴活动范围,看效果 随意~~

        //再Y轴上 为了确保第二个点 在第一个点之上,我把Y分成了上下两半 这样动画效果好一些  也可以用其他方法

        pointF.y=random.nextInt((mHeight-100)/scale);

        return pointF;


    }

    private class  BezierListener implements ValueAnimator.AnimatorUpdateListener{

        public BezierListener(View target) {
            this.target = target;
        }

        private View target;

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {

            //获取的曲线路径的值 不断更新View的值
            PointF pointF= (PointF) animation.getAnimatedValue();

            target.setX(pointF.x);
            target.setY(pointF.y);

            // 由明到暗的动画， 注意 fraction 值是 一个由 0 到 1 的值。
            target.setAlpha(1-animation.getAnimatedFraction());



        }
    }



    private  class  AnimEndListener extends AnimatorListenerAdapter{

        private View target;

        public AnimEndListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
        }
    }
}
