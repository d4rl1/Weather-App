package com.example.weatherapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherapp.databinding.FragmentSettingsBinding;
import com.example.weatherapp.databinding.ItemSettingRowBinding;

public class SettingsFragment extends Fragment {
    private static final String FEEDBACK_EMAIL = "duongminhhieu1414@gmail.com";
    private static final String GITHUB_URL = "https://github.com/d4rl1/Weather-App";

    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvVersion.setText(getString(R.string.version_format, BuildConfig.VERSION_NAME));

        setupRow(binding.rowFeedback, R.drawable.ic_mail, R.string.send_feedback);
        setupRow(binding.rowGithub, R.drawable.ic_open_in_browser, R.string.view_github);
        setupRow(binding.rowShare, R.drawable.ic_share, R.string.share_app);

        binding.rowFeedback.getRoot().setOnClickListener(v -> sendFeedback());
        binding.rowGithub.getRoot().setOnClickListener(v -> openGithub());
        binding.rowShare.getRoot().setOnClickListener(v -> shareApp());
    }

    private void setupRow(ItemSettingRowBinding row, int iconRes, int labelRes) {
        row.ivIcon.setImageResource(iconRes);
        row.tvLabel.setText(labelRes);
    }

    private void sendFeedback() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{FEEDBACK_EMAIL});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name) + " Feedback");

        if (emailIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            Toast.makeText(requireContext(), "No email app found", Toast.LENGTH_SHORT).show();
        }
    }

    private void openGithub() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_URL));
        try {
            startActivity(browserIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(requireContext(), "No browser app found", Toast.LENGTH_SHORT).show();
        }
    }

    private void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Check out WeatherNow! " + GITHUB_URL);

        if (shareIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(shareIntent, "Share WeatherNow via..."));
        } else {
            Toast.makeText(requireContext(), "No app available to share", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
