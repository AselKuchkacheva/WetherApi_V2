package com.example.wetherapi_v2.domain.remote;

import com.example.wetherapi_v2.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static RetrofitBuilder instance;
    private static Retrofit retrofit;

    public static RetrofitBuilder getInstance() {
        if (instance == null) {
            instance = new RetrofitBuilder();
        }
        return instance;
    }

    private RetrofitBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public WeatherApi getWeatherApi() {
        return retrofit.create(WeatherApi.class);
    }
}
