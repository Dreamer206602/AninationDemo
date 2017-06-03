package com.booboomx.aninationdemo.impl;

import com.booboomx.aninationdemo.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by booboomx on 17/5/13.
 */

public interface API {


    @GET("users/search/{keyword}")
    Observable<UserBean>getUser(@Path("keyword") String keyword);
}
