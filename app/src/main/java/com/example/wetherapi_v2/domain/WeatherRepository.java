package com.example.wetherapi_v2.domain;

import com.example.wetherapi_v2.domain.model.Weather;
import com.example.wetherapi_v2.domain.remote.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    public static void getWeather(String cityName, String apiKey, String units, String lang, WeatherCallback callback ){
        RetrofitBuilder.getInstance().getWeatherApi().getWeather(cityName, apiKey, units, lang)
                .enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.body() != null && response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(response.message());
                }
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public interface WeatherCallback{
        void onSuccess(Weather weather);
        void onFailure(String msg);
    }
}
