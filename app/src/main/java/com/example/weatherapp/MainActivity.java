package com.example.weatherapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.weatherapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private Fragment searchFragment;
    private Fragment savedFragment;
    private Fragment settingsFragment;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentManager fm = getSupportFragmentManager();

        if (savedInstanceState == null) {
            searchFragment = new SearchFragment();
            savedFragment = new SavedFragment();
            settingsFragment = new SettingsFragment();

            fm.beginTransaction()
                    .add(R.id.fragment_container, settingsFragment, "settings")
                    .hide(settingsFragment)
                    .add(R.id.fragment_container, savedFragment, "saved")
                    .hide(savedFragment)
                    .add(R.id.fragment_container, searchFragment, "search")
                    .commit();
            activeFragment = searchFragment;
        } else {
            searchFragment = fm.findFragmentByTag("search");
            savedFragment = fm.findFragmentByTag("saved");
            settingsFragment = fm.findFragmentByTag("settings");

            activeFragment = searchFragment;
            if (!savedFragment.isHidden()) {
                activeFragment = savedFragment;
            }
            if (!settingsFragment.isHidden()) {
                activeFragment = settingsFragment;
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_search) {
                showFragment(searchFragment);
            } else if (id == R.id.nav_saved) {
                showFragment(savedFragment);
            } else if (id == R.id.nav_settings) {
                showFragment(settingsFragment);
            } else {
                return false;
            }
            return true;
        });
    }

    private void showFragment(Fragment target) {
        if (target == activeFragment) {
            return;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .hide(activeFragment)
                .show(target)
                .commit();
        activeFragment = target;
    }
}