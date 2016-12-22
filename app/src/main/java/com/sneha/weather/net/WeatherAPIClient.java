package com.sneha.weather.net;

import android.util.Log;

import com.sneha.weather.BuildConfig;
import com.sneha.weather.callbacks.NetworkCallResponseListener;
import com.sneha.weather.model.FailureResponse;
import com.sneha.weather.model.WeatherMainModel;
import com.sneha.weather.utils.Constants;
import com.sneha.weather.utils.GeneralUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sneha Khadatare : 587823
 * on 6/8/2016.
 */
//Ref : https://inthecheesefactory.com/blog/retrofit-2.0/en
public class WeatherAPIClient {

    private WeatherRestAPI mWeatherRestAPIService;

    private static WeatherAPIClient sWeatherAPIClient;

    private WeatherAPIClient() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        if(BuildConfig.DEBUG){
            builder.client(GeneralUtils.getLoggingClient());
        }

        Retrofit retrofit = builder.build();

        mWeatherRestAPIService = retrofit.create(WeatherRestAPI.class);
    }

    public static WeatherAPIClient getInstance(){

        if(sWeatherAPIClient == null){
            sWeatherAPIClient = new WeatherAPIClient();
        }

        return sWeatherAPIClient;
    }

    public void loadWeather(final NetworkCallResponseListener<WeatherMainModel> networkListener) {

        Observable<WeatherMainModel> observable = mWeatherRestAPIService.loadWeather();


        observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<WeatherMainModel>() {
                    @Override
                    public void onCompleted() {
                        this.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        FailureResponse errorResponse = GeneralUtils.buildFailureResponse(throwable);
                        networkListener.onFailure(errorResponse);
                    }

                    @Override
                    public void onNext(WeatherMainModel weatherMainModel) {
                        networkListener.onSuccess(weatherMainModel);
                    }
                });

    }

}
