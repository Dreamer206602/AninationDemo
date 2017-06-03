package com.booboomx.aninationdemo.Ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.booboomx.aninationdemo.R;
import com.booboomx.aninationdemo.bean.UserBean;
import com.booboomx.aninationdemo.impl.API;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Retrofit 源码分析
 */
public class RetrofitCodeAnalyzeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_code_analyze);


       Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("BaseAPI")
                .client(new OkHttpClient())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .build();


        API api=retrofit.create(API.class);


        Observable<UserBean> user = api.getUser("123");



    }
}
