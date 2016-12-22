package com.sneha.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sneha.weather.R;
import com.sneha.weather.model.List;
import com.sneha.weather.utils.GeneralUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder> {

    Context mContext;
    private final ArrayList<List> mWeatherList;
    Map<String, String> daytoGerman = new HashMap<String, String>();
    private View vwhiteLine;

    public WeatherRecyclerAdapter(ArrayList<List> weatherList) {
        mWeatherList = weatherList;
    }


    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.row_weather_layout, viewGroup, false);

        daytoGerman.put("mon", "MO");
        daytoGerman.put("tue", "DI");
        daytoGerman.put("wed", "MI");
        daytoGerman.put("thu", "DO");
        daytoGerman.put("fri", "FR");
        daytoGerman.put("sat", "SA");
        daytoGerman.put("sun", "SO");
        vwhiteLine = (View)itemView.findViewById(R.id.vwhiteLine);

        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {


        List weatherList = mWeatherList.get(position);


        if (weatherList != null) {

            Calendar mydate = Calendar.getInstance();
            mydate.setTimeInMillis(Long.parseLong(weatherList.getDt()) * 1000L);
            String curdate[] = mydate.getTime().toString().split(" ");
            if (curdate != null & curdate.length > 2) {
                weatherList.setDt(curdate[2] + " " + curdate[1].toUpperCase());
                if (position == 0)
                    weatherList.setWeatherday(mContext.getResources().getString(
                            R.string.today));
                else {
                    weatherList.setWeatherday(curdate[0].toUpperCase());

                }
            }

            holder.tvDate.setText(weatherList.getDt());
            holder.tvDay.setText(weatherList.getWeatherday());

            if (!GeneralUtils.isEmpty(weatherList.getWeather().get(0).getDescription())) {
                holder.tvValCondition.setText(Character.toUpperCase(weatherList.getWeather().get(0).getDescription()
                        .trim().charAt(0))
                        + weatherList.getWeather().get(0).getDescription().trim().substring(1));

            } else {
                holder.tvValCondition.setText(mContext.getResources().getString(
                        R.string.not_available));

            }


            if (!GeneralUtils.isEmpty(weatherList.getTemp().getMax())) {
                holder.tvValMax.setText(Math
                        .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getMax())))
                        + mContext.getString(R.string.degcelcius)
                        + " / "
                        + Math.round(GeneralUtils.convertTemp(Math
                        .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getMax())))))
                        + mContext.getString(R.string.degfaran));

            }
            /*if (!GeneralUtils.isEmpty(weatherList.getTemp().getMax())) {
                holder.tvValMax.setText(Math
                        .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getMax())))
                        + mContext.getString(R.string.degcelcius)
                        + " / "
                        + Math.round(GeneralUtils.convertTemp(Float.parseFloat(String.valueOf(weatherList.getTemp().getMax()))))
                        + mContext.getString(R.string.degfaran));

            }*/ else {
                holder.tvValMax.setText("-");


            }

            if (!GeneralUtils.isEmpty(weatherList.getTemp().getMin())) {
                holder.tvValMin.setText(Math
                        .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getMin())))
                        + mContext.getString(R.string.degcelcius)
                        + " / "
                        + Math.round(GeneralUtils.convertTemp(Math
                        .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getMin())))))
                        + mContext.getString(R.string.degfaran));

            } else {
                holder.tvValMin.setText("-");

            }


            if (!GeneralUtils.isEmpty(weatherList.getTemp().getMorn())) {

                holder.tvValMorning.setText(Math
                        .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getMorn())))
                        + mContext.getString(R.string.degcelcius)
                        + " / "
                        + Math.round(GeneralUtils.convertTemp(Math
                        .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getMorn())))))
                        + mContext.getString(R.string.degfaran));

            } else {
                holder.tvValMorning.setText("-");

            }

            if (!GeneralUtils.isEmpty(weatherList.getTemp().getEve())) {
                holder.tvValEvening.setText(Math
                        .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getEve())))
                        + mContext.getString(R.string.degcelcius)
                        + " / "
                        + Math.round(GeneralUtils.convertTemp(Math
                        .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getEve())))))
                        + mContext.getString(R.string.degfaran));


            } else {

                holder.tvValEvening.setText("-");
            }

            if (!GeneralUtils.isEmpty(weatherList.getTemp().getNight())) {
                holder.tvValNight
                        .setText(Math
                                .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getNight())))
                                + mContext.getString(R.string.degcelcius)
                                + " / "
                                + Math.round(GeneralUtils.convertTemp(Math
                                .round(Float.parseFloat(String.valueOf(weatherList.getTemp().getNight())))))
                                + mContext.getString(R.string.degfaran));

            } else {

                holder.tvValNight.setText("-");


            }

            if (GeneralUtils.isEmpty(weatherList.getSpeed())) {
                holder.tvValWinds
                        .setText(mContext
                                .getResources().getString(
                                        R.string.not_available));

            } else {
                holder.tvValWinds
                        .setText((String.format(
                                "%.2f",
                                Float.parseFloat(weatherList.getSpeed()) * 2.23694))
                                .replace(",", ".")
                                + " "
                                + mContext.getString(R.string.milesperhour));
            }

            int imgsrc = 0;
            if (!GeneralUtils.isEmpty(weatherList.getWeather().get(0).getIcon())) {
                imgsrc = mContext.getResources().getIdentifier(
                        "w_" + weatherList.getWeather().get(0).getIcon(), "drawable",
                        mContext.getPackageName());

            }

            if (imgsrc <= 0 && mWeatherList.size()!= 0) {
                switch (weatherList.getWeatherCode()) {
                    case 200:
                    case 201:
                    case 202:
                    case 210:
                    case 211:
                    case 212:
                    case 221:
                    case 230:
                    case 231:
                    case 232:
                        imgsrc = mContext.getResources().getIdentifier("w_11d",
                                "drawable", mContext.getPackageName());
                        break;
                    case 300:
                    case 301:
                    case 302:
                    case 310:
                    case 311:
                    case 312:
                    case 321:
                    case 520:
                    case 521:
                    case 522:
                        imgsrc = mContext.getResources().getIdentifier("w_09d",
                                "drawable", mContext.getPackageName());
                        break;

                    case 500:
                    case 501:
                    case 502:
                    case 503:
                    case 504:
                        imgsrc = mContext.getResources().getIdentifier("w_10d",
                                "drawable", mContext.getPackageName());
                        break;
                    case 511:
                    case 600:
                    case 601:
                    case 602:
                    case 611:
                    case 621:
                        imgsrc = mContext.getResources().getIdentifier("w_13d",
                                "drawable", mContext.getPackageName());
                        break;
                    case 701:
                    case 711:
                    case 721:
                    case 731:
                    case 741:
                        imgsrc = mContext.getResources().getIdentifier("w_50d",
                                "drawable", mContext.getPackageName());
                        break;
                    case 800:
                        imgsrc = mContext.getResources().getIdentifier("w_01d",
                                "drawable", mContext.getPackageName());
                        break;
                    case 801:
                        imgsrc = mContext.getResources().getIdentifier("w_02d",
                                "drawable", mContext.getPackageName());
                        break;
                    case 802:
                        imgsrc = mContext.getResources().getIdentifier("w_03d",
                                "drawable", mContext.getPackageName());
                        break;
                    case 803:
                    case 804:
                        imgsrc = mContext.getResources().getIdentifier("w_04d",
                                "drawable", mContext.getPackageName());
                        break;
                }

            }
            // Fallback image
            if (imgsrc == 0)
                imgsrc = R.drawable.w_10d;
            holder.ivWeatherIcon.setImageResource(imgsrc);

            if(position == 2)
            {

                vwhiteLine.setVisibility(View.GONE);
            }

        }

    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ivWeatherIcon)
        ImageView ivWeatherIcon;
        @Bind(R.id.tvDay)
        TextView tvDay;
        @Bind(R.id.tvDate)
        TextView tvDate;
        @Bind(R.id.tvMin)
        TextView tvMin;
        @Bind(R.id.tvValMin)
        TextView tvValMin;
        @Bind(R.id.tvMax)
        TextView tvMax;
        @Bind(R.id.tvValMax)
        TextView tvValMax;

        @Bind(R.id.tvValMorning)
        TextView tvValMorning;
        @Bind(R.id.tvValEvening)
        TextView tvValEvening;
        @Bind(R.id.tvValNight)
        TextView tvValNight;
        @Bind(R.id.tvValWinds)
        TextView tvValWinds;
        @Bind(R.id.tvValCondition)
        TextView tvValCondition;


        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
