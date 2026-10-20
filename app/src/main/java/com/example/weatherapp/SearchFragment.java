package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherapp.databinding.FragmentSearchBinding;
import com.example.weatherapp.databinding.ItemCityCardBinding;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupCard(binding.cardLondon, new City("London", "City of London, Greater London",
                "🇬🇧", "Cloudy", 14, 78, 18, 12, R.drawable.ic_cloud));
        setupCard(binding.cardToronto, new City("Toronto", "Ontario",
                "🇨🇦", "Sunny", 22, 55, 12, 23, R.drawable.ic_sunny));
        setupCard(binding.cardTokyo, new City("Tokyo", "Tokyo-to",
                "🇯🇵", "Clear", 28, 70, 10, 31, R.drawable.ic_sunny));
        setupCard(binding.cardSydney, new City("Sydney", "New South Wales",
                "🇦🇺", "Sunny", 20, 60, 20, 19, R.drawable.ic_sunny));
        setupCard(binding.cardNewYork, new City("New York", "New York State",
                "🇺🇸", "Rainy", 11, 85, 25, 8, R.drawable.ic_rain));
    }

    private void setupCard(ItemCityCardBinding card, City city) {
        card.tvFlag.setText(city.getFlag());
        card.tvCityName.setText(city.getName());
        card.tvRegion.setText(city.getRegion());
        card.tvCondition.setText(city.getCondition() + " " + city.getTempC() + "°C");
        card.ivIcon.setImageResource(city.getIconRes());
        card.getRoot().setOnClickListener(v -> openDetail(city));
    }

    private void openDetail(City city) {
        Intent intent = new Intent(requireContext(), WeatherDetailActivity.class);
        intent.putExtra("city_name", city.getName());
        intent.putExtra("region", city.getRegion());
        intent.putExtra("condition", city.getCondition());
        intent.putExtra("temp_c", city.getTempC());
        intent.putExtra("temp_f", city.getTempF());
        intent.putExtra("humidity", city.getHumidity());
        intent.putExtra("wind_kph", city.getWindKph());
        intent.putExtra("feels_like", city.getFeelsLikeC());
        intent.putExtra("icon_res", city.getIconRes());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}