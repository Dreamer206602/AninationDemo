package com.booboomx.aninationdemo.Utils;

/**
 * Created by booboomx on 17/5/11.
 */

import android.content.Context;
import android.content.Intent;

import com.booboomx.aninationdemo.Ui.AnimationActivity;
import com.booboomx.aninationdemo.Ui.activity.TypeEvaluatorActivity;

/**
 * 跳转的工具类
 */
public class JumpUtils {

    public static void go2AnimationActivity(Context context){

        Intent intent=new Intent(context, AnimationActivity.class);
        context.startActivity(intent);
    }

    public static void go2TypeEvaluatorActivity(Context context){
        context.startActivity(new Intent(context, TypeEvaluatorActivity.class));
    }
}
