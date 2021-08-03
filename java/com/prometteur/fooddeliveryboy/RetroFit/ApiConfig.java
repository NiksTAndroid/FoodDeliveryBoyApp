package com.prometteur.fooddeliveryboy.RetroFit;

import android.util.Log;


import com.prometteur.fooddeliveryboy.Utils.StringConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {





    /*http://sairoses.com/phpmyadmin/
mydelivery-us
mydelivery@123*/


    private static String userSession = "";
    private static String userID = "";

    public static ApiInterface getApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        try {
            //userSession =   Constants.userDetails.getUserSession();
            //userID =        Constants.userDetails.getUserId();
        }
        catch (Exception e) {
            Log.v("DelievryBoy", e.getMessage());
        }

        Interceptor interceptor2 = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("user_session", userSession)
                        .addHeader("user_id", userID).build();
                return chain.proceed(request);
            }
        };


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120,TimeUnit.SECONDS)
                .callTimeout(120,TimeUnit.SECONDS)
                .build();


        return new Retrofit.Builder()
                .baseUrl(StringConstants.BASE_URL)//Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(ApiInterface.class);
    }

}