package com.booboomx.aninationdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by booboomx on 17/6/3.
 */

public class RadarView extends View {
    public static final String TAG=RadarView.class.getSimpleName();
    private int count = 6;//数据的个数
    private float angle = (float) (Math.PI * 2 / count);

    private float radius;
    private int centerX;
    private int centerY;
    private String[] titles = {"a", "b", "c", "d", "e", "f"};

    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20};
    private float maxValue = 100;
    private Paint mainPaint = new Paint();
    private Paint valuePaint=new Paint();
    private Paint textPaint=new Paint();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
//        postInvalidate();
        Log.i(TAG, "onSizeChanged: centerX->"+centerX+";;centerY->"+centerY);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStrokeWidth(4f);
        mainPaint.setStyle(Paint.Style.STROKE);


        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(28f);

        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    /**
     * 绘制多边形
     * @param canvas
     */
    public void drawPolygon(Canvas canvas) {
        Log.i(TAG, "drawPolygon: radius"+radius+";;angle->"+angle);
        Path path = new Path();
        float r = radius / (count - 1);
        for (int i = 0; i < count; i++) {
            float curR = r * i;//当前的半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                    Log.i(TAG, "(i,j)->"+i+","+j+"-->drawPolygon: centerX + curR->"+centerX +"+"+curR+";;centerY->"+centerY);

                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标

                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                    Log.i(TAG,  "(i,j)->"+i+","+j+"drawPolygon2: x->"+x+";;y->"+y);
                }

            }
            path.close();
            canvas.drawPath(path, mainPaint);
        }

    }

    /**
     * 绘制直线
     *
     * @param canvas
     */
    public void  drawLine(Canvas canvas){
        Log.i(TAG, "drawLine: radius"+radius+";;angle->"+angle);
        Path path=new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX,centerY);

            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x,y);
            canvas.drawPath(path,mainPaint);
        }


    }

    /**
     * 绘制文字
     * @param canvas
     */
    public void  drawText(Canvas canvas){

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        float fontHeight = fontMetrics.descent - fontMetrics.ascent;

        for (int i = 0; i < count; i++) {

            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));


            if(angle*i>=0&& angle*i<=Math.PI/2){//第四象限
                canvas.drawText(titles[i],x,y,textPaint);
            }else if(angle*i>=3*Math.PI/2&& angle<=Math.PI*2){//第三象限
                canvas.drawText(titles[i],x,y,textPaint);
            }else if(angle*i>Math.PI/2&& angle*i<=Math.PI){//第二象限
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i],x-dis,y,textPaint);

            }else if(angle*i>=Math.PI&& angle*i<3*Math.PI/2){
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i],x-dis,y,textPaint);

            }

        }



    }

    /**
     * 绘制区域
     * @param canvas
     */
    public void drawRegion(Canvas canvas){

        Path path=new Path();
        valuePaint.setAlpha(255);

        for (int i = 0; i < count; i++) {

            double percent = data[i] / maxValue;

            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);


            if(i==0){

                path.moveTo(x,centerY);
            }else {
                path.lineTo(x,y);
            }


            //绘制小圆点
            canvas.drawCircle(x,y,10,valuePaint);

        }

        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path,valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充的区域
         valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path,valuePaint);


    }


    //设置标题
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    //设置数值
    public void setData(double[] data) {
        this.data = data;
    }

    //设置最大数值
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    //设置蜘蛛网颜色
    public void setMainPaintColor(int color){
        mainPaint.setColor(color);
    }

    //设置标题颜色
    public void setTextPaintColor(int color){
        textPaint.setColor(color);
    }

    //设置覆盖局域颜色
    public void setValuePaintColor(int color){
        valuePaint.setColor(color);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLine(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }
}
