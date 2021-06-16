package com.example.wetherapi_v2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wetherapi_v2.R;
import com.example.wetherapi_v2.databinding.ActivityMainBinding;
import com.example.wetherapi_v2.domain.model.Weather;
import com.example.wetherapi_v2.domain.remote.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
//        getWeather();
    }

//    private void getWeather() {
//        String API_KEY = "8dc74f6d7378236f0048e2e74c4f1c78";
//        RetrofitBuilder.getInstance().getWeatherApi().getWeather("Bishkek", API_KEY, "metric", "ru").enqueue(new Callback<Weather>() {
//            @Override
//            public void onResponse(Call<Weather> call, Response<Weather> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<Weather> call, Throwable t) {
//
//            }
//        });
//    }
}