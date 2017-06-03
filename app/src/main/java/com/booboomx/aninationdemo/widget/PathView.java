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

/**
 * 练习 Path属性
 */
public class PathView extends View {

    public static final String TAG = PathView.class.getSimpleName();
    private Canvas mCanvas = new Canvas();
    private Path mPath = new Path();
    private int mWidth, mHeight;
    private Paint mPaint = new Paint();

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        Log.i(TAG, "onSizeChanged: mWidth->" + mWidth + ";;mHeight->" + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        //1.lineTo,moveTo,setLastPosition,close
//        mPath.lineTo(200,200);
//        mPath.moveTo(200,100);
//        mPath.setLastPoint(200,100);
//        mPath.lineTo(200,0);




//        mPath.close();
//         canvas.drawPath(mPath,mPaint);


        // 2.基本数据类型
        /**
         * // 第一类(基本形状)
         // 圆形
         public void addCircle (float x, float y, float radius, Path.Direction dir)
         // 椭圆
         public void addOval (RectF oval, Path.Direction dir)
         // 矩形  Path.Direction.CW 顺时针  Path.Direction.CCW逆时针
         public void addRect (float left, float top, float right, float bottom, Path.Direction dir)
         public void addRect (RectF rect, Path.Direction dir)
         // 圆角矩形
         public void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
         public void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)
         */
//        mPath.addRect(-200,-200,200,200,Path.Direction.CW);
//        mPath.setLastPoint(-300,300);
//        canvas.drawPath(mPath,mPaint);

        /**
         * // 第二类(Path)
         // path
         public void addPath (Path src)
         public void addPath (Path src, float dx, float dy)
         public void addPath (Path src, Matrix matrix)
         */
//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
//        Path src=new Path();
//        mPath.addRect(-200,-200,200,200,Path.Direction.CW);
//
//        src.addCircle(0,0,100, Path.Direction.CW);
//
//        mPath.addPath(src,0,200);
//        canvas.drawPath(mPath,mPaint);


        /**
         * // 第三类(addArc与arcTo)
         // addArc
         public void addArc (RectF oval, float startAngle, float sweepAngle)
         // arcTo
         public void arcTo (RectF oval, float startAngle, float sweepAngle)
         public void arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
         */

//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
//
////        mPath.lineTo(100,100);
//
//        RectF rectF=new RectF(0,0,300,300);
////        mPath.addArc(rectF,0,270);
//        mPath.arcTo(rectF,0,270,true);
//        canvas.drawPath(mPath,mPaint);


        /**
         * 方法预览：

         public void set (Path src)
         */

//        canvas.scale(-1,1);
//
//        mPath.addRect(-200,-200,200,200, Path.Direction.CW);
//        Path src=new Path();
//        src.addCircle(0,0,100, Path.Direction.CW);
//
//        mPath.set(src);
//        canvas.drawPath(mPath,mPaint);


        /**
         * offset

         方法预览：

         public void offset (float dx, float dy)
         public void offset (float dx, float dy, Path dst)
         这个的作用也很简单，就是对path进行一段平移，它和Canvas中的translate作用很像，但Canvas作用于整个画布，而path的offset只作用于当前path。

         但是第二个方法最后怎么会有一个path作为参数？

         其实第二个方法中最后的参数dst是存储平移后的path的。

         dst状态	效果
         dst不为空	将当前path平移后的状态存入dst中，不会影响当前path
         dst为空(null)	平移将作用于当前path，相当于第一种方法
         */
//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
//
//        mPath.addCircle(0,0,100, Path.Direction.CW);
//
//        Path dst=new Path();
//        dst.addRect(-200,-200,200,200, Path.Direction.CW);
//        mPath.offset(300,0,dst);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawPath(mPath,mPaint);
//
//        mPaint.setColor(Color.BLUE);
//        canvas.drawPath(dst,mPaint);





    }
}
