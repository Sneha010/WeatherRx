package com.sneha.weather.model;

/**
 * Created by Sneha Khadatare : 587823
 * on 6/1/2016.
 */
public class FailureResponse {


    /*Status Codes*/
    public static final int NO_INTERNET = 100;
    public static final int SERVER_ERROR = 101;
    public static final int TAG_NOT_FOUND = 102;

    private int mStatusCode;
    private String mErrorMessage;


    public FailureResponse() {

    }

    public FailureResponse(int statusCode, String errorMessage) {
        mStatusCode = statusCode;
        mErrorMessage = errorMessage;
    }


    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int statusCode) {
        mStatusCode = statusCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorMessage = errorMessage;
    }
}
