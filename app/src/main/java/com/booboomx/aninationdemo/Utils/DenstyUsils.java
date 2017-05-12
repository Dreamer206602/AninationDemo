package com.booboomx.aninationdemo.Utils;

import android.content.Context;

/**
 * Created by booboomx on 17/5/12.
 */

public class DenstyUsils {


    public static  int px2dip(Context context,float value){
        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (value/scale+0.5f);
    }

    public static  int dip2px(Context context,float value){
       final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value*scale+0.5f);
    }



}
