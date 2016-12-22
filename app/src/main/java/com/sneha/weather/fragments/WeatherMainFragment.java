package com.sneha.weather.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.sneha.weather.R;
import com.sneha.weather.adapter.WeatherRecyclerAdapter;
import com.sneha.weather.callbacks.NetworkCallResponseListener;
import com.sneha.weather.model.FailureResponse;
import com.sneha.weather.model.WeatherMainModel;
import com.sneha.weather.presenter.WeatherPresenter;
import com.sneha.weather.presenter.WeatherPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherMainFragment extends BaseFragment {


    @Bind(R.id.rvWeatherList)
    RecyclerView rvWeatherList;

    @Bind(R.id.tvCredits)
    TextView tvCredits;

    @Bind(R.id.swipeToRefresh_container)
    SwipeRefreshLayout mSwipeToRefreshContainer;

    @Bind(R.id.progressBar)
    ContentLoadingProgressBar mProgressBar;

    @Bind(R.id.errorMessage)
    TextView mErrorMessage;

    @Bind(R.id.rlError)
    View mRlError;

    private boolean isRefresh = false;
    private WeatherPresenter mWeatherPresenter;
    private WeatherMainModel mWeatherMainModel;


    public static WeatherMainFragment newInstance() {
        WeatherMainFragment fragment = new WeatherMainFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_main_layout, container, false);
        ButterKnife.bind(this, view);
        setUpLink();
        mWeatherPresenter = new WeatherPresenterImpl(mWeatherNetworkCallResponseListener);

        return view;
    }

    private void setUpLink() {
        tvCredits.setLinkTextColor(getResources().getColor(R.color.white));
        tvCredits
                .setText(Html
                        .fromHtml("<html>Credits:    <a href='http://openweathermap.org/city/6544881' style='text-decoration:none'>OpenWeatherMap</a></html>"));
        tvCredits.setMovementMethod(LinkMovementMethod.getInstance());

    }


    private void setUpMyRecyclerView() {

        rvWeatherList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvWeatherList.setLayoutManager(llm);



    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        mSwipeToRefreshContainer.setRefreshing(true);

        mSwipeToRefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "Hi", Toast.LENGTH_SHORT).show();
                isRefresh = true;
                mWeatherMainModel = null;
                mWeatherPresenter.fetchWeather();
            }
        });

        setUpMyRecyclerView();

        retrieveWeather();
    }




    private void retrieveWeather() {

        showProgress();

        if (mWeatherMainModel == null) {
            mWeatherPresenter.fetchWeather();
        } else {
            displayWeather();
        }
    }

    @NonNull
    @Override
    public String getTitle() {
        return getResources().getString(R.string.h_weather);
    }


    private void displayWeather() {

        WeatherRecyclerAdapter weatherAdapter = new WeatherRecyclerAdapter(
                mWeatherMainModel.getList());

        //Decoration Pattern
       rvWeatherList.setItemAnimator(new FadeInAnimator());
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(weatherAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        rvWeatherList.setAdapter(scaleAdapter);

        showContent();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private NetworkCallResponseListener<WeatherMainModel> mWeatherNetworkCallResponseListener = new NetworkCallResponseListener<WeatherMainModel>() {
        @Override
        public void onSuccess(WeatherMainModel successResponse) {
            if(isAdded()){
                if (successResponse != null) {
                    mWeatherMainModel = successResponse;
                    displayWeather();
                } else {
                    showError(getString(R.string.tag_not_found));
                }
            }
        }

        @Override
        public void onFailure(FailureResponse failureResponse) {

            if(isAdded()){
                switch (failureResponse.getStatusCode()) {
                    case FailureResponse.NO_INTERNET:
                        showError(getString(R.string.no_internet_connectivity));
                        break;
                    case FailureResponse.SERVER_ERROR:
                        showError(getString(R.string.server_error));
                        break;
                    case FailureResponse.TAG_NOT_FOUND:
                        showError(getString(R.string.tag_not_found));
                        break;
                    default:
                        showError(getString(R.string.server_error));
                }
            }

        }
    };

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.bringToFront();
        mRlError.setVisibility(View.GONE);
        mSwipeToRefreshContainer.setVisibility(View.GONE);
    }

    public void showError(String message) {
        mRlError.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mSwipeToRefreshContainer.setVisibility(View.GONE);
        mErrorMessage.setText(message);

    }

    public void showContent() {
        mSwipeToRefreshContainer.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mRlError.setVisibility(View.GONE);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.rlError)
    public void onRetryRequest() {
        retrieveWeather();
    }

}
