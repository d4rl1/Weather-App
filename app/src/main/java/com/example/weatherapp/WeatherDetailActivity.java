package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.databinding.ActivityWeatherDetailBinding;

public class WeatherDetailActivity extends AppCompatActivity {
    private ActivityWeatherDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String cityName = intent.getStringExtra("city_name");
        String region = intent.getStringExtra("region");
        String condition = intent.getStringExtra("condition");
        int tempC = intent.getIntExtra("temp_c", 0);
        int tempF = intent.getIntExtra("temp_f", 32);
        int humidity = intent.getIntExtra("humidity", 0);
        int windKph = intent.getIntExtra("wind_kph", 0);
        int feelsLike = intent.getIntExtra("feels_like", 0);
        int iconRes = intent.getIntExtra("icon_res", R.drawable.ic_sunny);

        binding.toolbar.setTitle(cityName);
        binding.toolbar.setSubtitle(region);
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.ivIcon.setImageResource(iconRes);
        binding.tvCondition.setText(condition);
        binding.tvTempC.setText(tempC + "°C");
        binding.tvTempF.setText(tempF + "°F");
        binding.tvHumidity.setText(humidity + "%");
        binding.tvWind.setText(windKph + " kph");
        binding.tvFeelsLike.setText(feelsLike + "°C");
    }
}