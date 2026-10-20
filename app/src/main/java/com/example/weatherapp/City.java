package com.example.weatherapp;

public class City {
    private final String name;
    private final String region;
    private final String flag;
    private final String condition;
    private final int tempC;
    private final int humidity;
    private final int windKph;
    private final int feelsLikeC;
    private final int iconRes;

    public City(String name, String region, String flag, String condition,
                int tempC, int humidity, int windKph, int feelsLikeC, int iconRes) {
        this.name = name;
        this.region = region;
        this.flag = flag;
        this.condition = condition;
        this.tempC = tempC;
        this.humidity = humidity;
        this.windKph = windKph;
        this.feelsLikeC = feelsLikeC;
        this.iconRes = iconRes;
    }

    public String getName() { return name; }
    public String getRegion() { return region; }
    public String getFlag() { return flag; }
    public String getCondition() { return condition; }
    public int getTempC() { return tempC; }
    public int getHumidity() { return humidity; }
    public int getWindKph() { return windKph; }
    public int getFeelsLikeC() { return feelsLikeC; }
    public int getIconRes() { return iconRes; }

    public int getTempF() { return Math.round(tempC * 9 / 5f + 32); }
}