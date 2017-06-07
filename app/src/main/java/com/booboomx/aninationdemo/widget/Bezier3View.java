package com.booboomx.aninationdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by booboomx on 17/6/4.
 */

public class Bezier3View extends View {
    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置

    private Paint mPaint;
    private int mCenterX, mCenterY;
    private PointF mCenter = new PointF(0, 0);

    private float mCircleRadius = 200;//圆的半径
    private float mDifference = mCircleRadius * C;//圆的控制点和数据点的差值


    private float[] mData = new float[8];//顺时针记录绘制圆形的四个数据点
    private float[] mCtrl = new float[16];//顺时针记录绘制圆形的八个控制点

    private float mDuration = 1000;
    private float mCurrent = 0;
    private float mCount = 100;
    private float mPiece = mDuration / mCount;


    public Bezier3View(Context context) {
        this(context, null);
    }

    public Bezier3View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bezier3View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {


        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60f);

        //初始化数据点
        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;


        //初始化控制点
        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;


        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;

        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];

        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];


        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;

        mCtrl[12] = mData[6] ;
        mCtrl[13] = mData[7]+ mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = w / 2;
        mCenterY = h / 2;

    }

    /**
     * 绘制坐标系
     *
     * @param canvas
     */
    public void drawCoorDinateSystem(Canvas canvas) {

        canvas.save();
        canvas.translate(mCenterX, mCenterY);
        canvas.scale(1, -1);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0, -2000, 0, 2000, paint);
        canvas.drawLine(-2000, 0, 2000, 0, paint);

        canvas.restore();

    }

    /**
     * 绘制辅助线
     *
     * @param canvas
     */
    public void drawAuxiliaryLine(Canvas canvas) {
        //绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);

        for (int i = 0; i < 8; i += 2) {

            canvas.drawPoint(mData[i], mData[i + 1], mPaint);
        }

        for (int i = 0; i < 16; i += 2) {

            canvas.drawPoint(mCtrl[i], mCtrl[i + 1], mPaint);
        }

        mPaint.setStrokeWidth(4);

        for (int i = 2, j = 2; i < 8; i += 2, j += 4) {

            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j], mCtrl[j + 1], mPaint);
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j + 2], mCtrl[j + 3], mPaint);


        }

        canvas.drawLine(mData[0], mData[1], mCtrl[0], mCtrl[1], mPaint);
        canvas.drawLine(mData[0], mData[1], mCtrl[14], mCtrl[15], mPaint);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCoorDinateSystem(canvas);//绘制坐标系
        canvas.translate(mCenterX, mCenterY);
        canvas.scale(1, -1);

        drawAuxiliaryLine(canvas);

        //绘制贝赛尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(mData[0], mData[1]);

        path.cubicTo(mCtrl[0], mCtrl[1], mCtrl[2], mCtrl[3], mData[2], mData[3]);
        path.cubicTo(mCtrl[4], mCtrl[5], mCtrl[6], mCtrl[7], mData[4], mData[5]);
        path.cubicTo(mCtrl[8], mCtrl[9], mCtrl[10], mCtrl[11], mData[6], mData[7]);
        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15], mData[0], mData[1]);


        canvas.drawPath(path, mPaint);


        mCurrent += mPiece;
        if (mCurrent < mDuration) {
            mData[1] -= 120 / mCount;
            mCtrl[7] += 80 / mCount;
            mCtrl[9] += 80 / mCount;

            mCtrl[4] -= 20 / mCount;
            mCtrl[10] += 20 / mCount;

            postInvalidateDelayed((long) mPiece);
        }
    }


}
