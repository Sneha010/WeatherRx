
package com.sneha.weather.model;

import java.util.ArrayList;


public class List {

    private String weatherday;
    private String dt;
    private Temp temp;
    private Double pressure;
    private Integer humidity;
    private ArrayList<Weather> weather = new ArrayList<>();
    private String speed;
    private Integer deg;
    private Integer clouds;
    private Double rain;
    private int weatherCode;



    /**
     * 
     * @return
     *     The dt
     */
    public String getDt() {
        return dt;
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    public void setDt(String dt) {
        this.dt = dt;
    }

    /**
     * 
     * @return
     *     The temp
     */
    public Temp getTemp() {
        return temp;
    }

    /**
     * 
     * @param temp
     *     The temp
     */
    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    /**
     * 
     * @return
     *     The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * 
     * @param pressure
     *     The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * 
     * @return
     *     The humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * 
     * @param humidity
     *     The humidity
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * 
     * @return
     *     The weather
     */
    public ArrayList<Weather> getWeather() {
        return weather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    /**
     * 
     * @return
     *     The speed
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * 
     * @param speed
     *     The speed
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }

    /**
     * 
     * @return
     *     The deg
     */
    public Integer getDeg() {
        return deg;
    }

    /**
     * 
     * @param deg
     *     The deg
     */
    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    /**
     * 
     * @return
     *     The clouds
     */
    public Integer getClouds() {
        return clouds;
    }

    /**
     * 
     * @param clouds
     *     The clouds
     */
    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    /**
     * 
     * @return
     *     The rain
     */
    public Double getRain() {
        return rain;
    }

    /**
     * 
     * @param rain
     *     The rain
     */
    public void setRain(Double rain) {
        this.rain = rain;
    }

    public String getWeatherday() {
        return weatherday;
    }

    public void setWeatherday(String weatherday) {
        this.weatherday = weatherday;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }
}
