package com.booboomx.aninationdemo.typeEvaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by booboomx on 17/5/12.
 */

public class HeartBezierEvaluator implements TypeEvaluator<PointF> {

    private PointF pointF1;
    private PointF pointF2;

    public HeartBezierEvaluator(PointF pointF1, PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float t, PointF startValue, PointF endValue) {
        // 三阶Bezier公式 。。

        float tLeft = 1.0f - t;
        PointF pointF=new PointF();

        pointF.x=tLeft*tLeft*tLeft*(startValue.x)
                +3*tLeft*tLeft*t*(pointF1.x)
                +3*tLeft*t*t*(pointF2.x)
                +t*t*t*(endValue.x);

        pointF.y=tLeft*tLeft*tLeft*(startValue.y)
                +3*tLeft*tLeft*t*(pointF1.y)
                +3*tLeft*t*t*(pointF2.y)
                +t*t*t*(endValue.y);
        return pointF;
    }
}
