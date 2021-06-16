package com.example.wetherapi_v2.domain.remote;


import com.example.wetherapi_v2.domain.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET(EndPointsAPI.GET_WEATHER)
    Call<Weather> getWeather(
            @Query(EndPointsAPI.QUERY) String cityName,
            @Query(EndPointsAPI.APP_ID) String appId,
            @Query(EndPointsAPI.UNITS) String units,
            @Query(EndPointsAPI.LANG) String lang);
}
