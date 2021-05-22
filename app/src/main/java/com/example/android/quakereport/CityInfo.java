package com.example.android.quakereport;

public class CityInfo {
    private  double magnitude;
    private  String cityName;
    private long mTimeInMilliseconds;
    private String mUrl;

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public CityInfo(double magnitude, String cityName, long mTimeInMilliseconds, String url){
        this.magnitude=magnitude;
        this.cityName=cityName;
        this.mTimeInMilliseconds=mTimeInMilliseconds;
        mUrl=url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public void setmTimeInMilliseconds(long mtimeInMilliseconds) {
        mTimeInMilliseconds = mtimeInMilliseconds;
    }
}
