package com.example.bankapp.Interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static String BASE_URL = "https://sandbox.safaricom.co.ke/";

    public static Retrofit retrofit;
    /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();*/

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
