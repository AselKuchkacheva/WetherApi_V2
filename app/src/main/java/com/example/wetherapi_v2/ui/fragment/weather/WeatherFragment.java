package com.example.wetherapi_v2.ui.fragment.weather;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wetherapi_v2.R;
import com.example.wetherapi_v2.databinding.ActivityMainBinding;
import com.example.wetherapi_v2.databinding.FragmentWeatherBinding;
import com.example.wetherapi_v2.domain.WeatherRepository;
import com.example.wetherapi_v2.domain.model.Main;
import com.example.wetherapi_v2.domain.model.Weather;
import com.example.wetherapi_v2.domain.remote.EndPointsAPI;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherFragment extends Fragment {

    protected String cityName = "Bishkek";
    protected String units = "metric";
    protected String lang = "ru";
    protected String apiKey = "8dc74f6d7378236f0048e2e74c4f1c78";

    protected FragmentWeatherBinding binding;

    private NavController navController;
    private WeatherWeekAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater);
        return binding.getRoot();
    }

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getCountry();
        adapter = new WeatherWeekAdapter();
        binding.rvWeekWeather.setAdapter(adapter);

        WeatherRepository.getWeather(cityName, apiKey, units, lang, new WeatherRepository.WeatherCallback() {
            @Override
            public void onSuccess(Weather weather) {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show();
                showModel(weather);
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

        openCity();
    }

    private void getCountry() {
        if (getArguments() != null) {
            String city = getArguments().getString("city");
            if (city != null) {
                cityName = city;
            }
        }
    }

    private void openCity() {

        binding.tvPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_weatherFragment_to_cityFragment);
            }
        });
    }

    private void showModel(Weather weather) {
        setWeatherIndicators(weather);
        setTextViews(weather);
        setTiming(weather);
    }

    private void setWeatherIndicators(Weather weather) {
        String result = "https://openweathermap.org/img/wn/" + weather.getWeather().get(0).getIcon() + "@2x.png";
        Glide
                .with(this)
                .load(result)
                .centerCrop()
                .into(binding.ivCloudSun);
    }

    private void setTiming(Weather weather) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault());
        SimpleDateFormat timeFormatAmPm = new SimpleDateFormat("hh:mm a", Locale.getDefault() );
        long getDateAndTime = Long.valueOf(weather.getDt()) * 1000;

        binding.tvDate.setText(dateFormat.format(getDateAndTime));
        binding.tvTime.setText(timeFormatAmPm.format(getDateAndTime));
        binding.tvSunriseTime.setText(timeFormatAmPm.format(Long.valueOf(weather.getSys().getSunrise()) * 1000));
        binding.tvSunsetTime.setText(timeFormatAmPm.format(Long.valueOf(weather.getSys().getSunset()) * 1000));

        double dayTime = (Double.valueOf(weather.getSys().getSunset()) - Double.valueOf(weather.getSys().getSunrise())) / 3600;
        String stringDayTime = new DecimalFormat("##.##").format(dayTime);

        binding.tvDaytimeHr.setText(stringDayTime.substring(0, 2));
        binding.tvDaytimeMin.setText(stringDayTime.substring(3));

        SimpleDateFormat timeFormat24hr = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeDayNight = timeFormat24hr.format(getDateAndTime);
        String hourOfDayTime = timeDayNight.substring(0, 2);
        boolean isDayTime = Integer.parseInt(hourOfDayTime) >= 5 && Integer.parseInt(hourOfDayTime) <= 18;
        if (isDayTime)
            binding.ivDayCity.setImageResource(R.drawable.ic_graphic_city);
        else binding.ivDayCity.setImageResource(R.drawable.ic_graphic_city_night);

    }

    private void setTextViews(Weather weather) {
        binding.tvPlace.setText(String.format("%s, %s", weather.getName(), weather.getSys().getCountry()));

        binding.tvTemperature.setText(String.valueOf((int) Math.round(weather.getMain().getTemp())));
        binding.tvSunny.setText(weather.getWeather().get(0).getMain());

        binding.tv33Max.setText(String.valueOf((int) Math.round(weather.getMain().getTempMax())));
        binding.tv33Min.setText(String.valueOf((int) Math.round(weather.getMain().getTempMin())));

        binding.tvHumidityPercent.setText(String.valueOf(weather.getMain().getHumidity()));

        binding.tvPressureBar.setText(String.valueOf((double) weather.getMain().getPressure() / 1000));

        binding.tvWindKmH.setText(String.valueOf(Double.valueOf(weather.getWind().getSpeed())));
    }
}