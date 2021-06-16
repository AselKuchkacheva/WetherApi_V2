package com.example.wetherapi_v2.ui.fragment.weather;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetherapi_v2.R;
import com.example.wetherapi_v2.databinding.ItemWeekWeatherBinding;
import com.example.wetherapi_v2.domain.model.WeekReport;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.R)
public class WeatherWeekAdapter extends RecyclerView.Adapter<WeatherWeekAdapter.WeatherWeekVH> {

    private ItemWeekWeatherBinding binding;

    List<Integer> listPic = List.of(
            R.drawable.ic_sun,
            R.drawable.ic_cloud_small,
            R.drawable.ic_hazy,
            R.drawable.ic_sun,
            R.drawable.ic_hazy,
            R.drawable.ic_sun);
    List<String> listDays = List.of("Mon 21", "Tue 22", "Wed 25", "Thu 5", "Fri 2", "Sat 15");
    List<String> listCelsiusUp = List.of("35", "30", "34", "7", "4", "8");
    List<String> listCelsiusDown = List.of("26", "23", "29", "2", "1", "5");



    @NonNull
    @Override
    public WeatherWeekVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemWeekWeatherBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new WeatherWeekVH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherWeekVH holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return listPic.size();
    }

    public class WeatherWeekVH extends RecyclerView.ViewHolder {

        public WeatherWeekVH(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(int position) {
            binding.ivWeekDay.setImageResource(listPic.get(position));
            binding.tvWeekDay.setText(listDays.get(position));
            binding.tvMaxTempWeek.setText(listCelsiusUp.get(position));
            binding.tvMinTempWeek.setText(listCelsiusDown.get(position));
        }

    }
}
