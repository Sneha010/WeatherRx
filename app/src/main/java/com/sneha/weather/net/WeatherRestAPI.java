package com.sneha.weather.net;

import com.sneha.weather.BuildConfig;
import com.sneha.weather.model.WeatherMainModel;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Sneha Khadatare : 587823
 * on 6/8/2016.
 */
public interface WeatherRestAPI {


    @GET("data/2.5/forecast/daily?q=Amsterdam&APPID="+ BuildConfig.OPEN_WEATHER_MAP_API_KEY+"&units=metric&cnt=3&lang=en")
    Observable<WeatherMainModel> loadWeather();
}
