package com.booboomx.aninationdemo.Utils;

/**
 * Created by booboomx on 17/5/11.
 */

import android.content.Context;
import android.content.Intent;

import com.booboomx.aninationdemo.Ui.activity.AnimationActivity;
import com.booboomx.aninationdemo.Ui.activity.AnimatorSetActivity;
import com.booboomx.aninationdemo.Ui.activity.BezierActivity;
import com.booboomx.aninationdemo.Ui.activity.InterpolatorActivity;
import com.booboomx.aninationdemo.Ui.activity.RotationActivity;
import com.booboomx.aninationdemo.Ui.activity.RubbingActivity;
import com.booboomx.aninationdemo.Ui.activity.ShoppingCarActivity;
import com.booboomx.aninationdemo.Ui.activity.TypeEvaluatorActivity;

/**
 * 跳转的工具类
 */
public class JumpUtils {

    public static void go2AnimationActivity(Context context) {

        Intent intent = new Intent(context, AnimationActivity.class);
        context.startActivity(intent);
    }

    public static void go2TypeEvaluatorActivity(Context context) {
        context.startActivity(new Intent(context, TypeEvaluatorActivity.class));
    }

    public static void go2InterpolatorActivity(Context context) {
        context.startActivity(new Intent(context, InterpolatorActivity.class));
    }

    public static void go2AnimatorSetActivity(Context context) {
        context.startActivity(new Intent(context, AnimatorSetActivity.class));
    }

    public static void go2RotationActivity(Context context) {
        context.startActivity(new Intent(context, RotationActivity.class));
    }

    public static void go2BezierActivity(Context context) {
        context.startActivity(new Intent(context, BezierActivity.class));
    }

    public static void go2ShoppingActivity(Context context) {
        context.startActivity(new Intent(context, ShoppingCarActivity.class));
    }

    public static void go2RubbingLayoutActivity(Context context) {
        context.startActivity(new Intent(context, RubbingActivity.class));
    }


}
