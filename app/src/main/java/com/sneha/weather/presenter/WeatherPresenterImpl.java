package com.sneha.weather.presenter;

import com.sneha.weather.callbacks.NetworkCallResponseListener;
import com.sneha.weather.model.WeatherMainModel;
import com.sneha.weather.net.WeatherAPIClient;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sneha Khadatare : 587823
 * on 6/8/2016.
 */
public class WeatherPresenterImpl implements WeatherPresenter {

    private final NetworkCallResponseListener<WeatherMainModel> mWeatherNetworkCallResponseListener;
    private WeatherAPIClient mWeatherAPIClient;


    public WeatherPresenterImpl(NetworkCallResponseListener<WeatherMainModel> weatherNetworkCallResponseListener) {

        mWeatherNetworkCallResponseListener = weatherNetworkCallResponseListener;
        mWeatherAPIClient = WeatherAPIClient.getInstance();

    }


    @Override
    public void fetchWeather() {
        mWeatherAPIClient.loadWeather(mWeatherNetworkCallResponseListener);
    }

}
