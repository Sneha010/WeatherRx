package com.sneha.weather.callbacks;


import com.sneha.weather.model.FailureResponse;

/**
 * Created by Sneha Khadatare : 587823
 * on 6/1/2016.
 */

public interface NetworkCallResponseListener<T> {

    void onSuccess(T successResponse);
    void onFailure(FailureResponse failureResponse);
}
