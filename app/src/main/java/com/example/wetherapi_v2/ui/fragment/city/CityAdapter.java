package com.example.wetherapi_v2.ui.fragment.city;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetherapi_v2.databinding.FragmentCityBinding;
import com.example.wetherapi_v2.databinding.ItemCityBinding;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.R)
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityVH> {

    List<String> cityList = List.of(
            "Bishkek", "London",
            "Moscow", "Miami",
            "Montreal", "Munich",
            "Muscat", "Manila",
            "Tokyo", "Dubai",
            "Barcelona", "Rome",
            "Chicago", "Toronto");

    private ItemCityBinding binding;
    private OnClickCityAdapter onClickCityAdapter;

    public CityAdapter(OnClickCityAdapter onClickCityAdapter) {
        this.onClickCityAdapter = onClickCityAdapter;
    }

    @NonNull
    @Override
    public CityVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CityVH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CityVH holder, int position) {
        binding.tvCitiesOnRvCity.setText(cityList.get(position));
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCityAdapter.onClick(cityList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class CityVH extends RecyclerView.ViewHolder {
        public CityVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    interface OnClickCityAdapter{
        void onClick(String city);
    }
}
