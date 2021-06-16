package com.example.wetherapi_v2.ui.fragment.city;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.Barrier;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wetherapi_v2.R;
import com.example.wetherapi_v2.databinding.FragmentCityBinding;

public class CityFragment extends Fragment implements CityAdapter.OnClickCityAdapter {

    private FragmentCityBinding binding;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCityBinding.inflate(inflater);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CityAdapter adapter = new CityAdapter(this);
        binding.rvCityFragmentCity.setAdapter(adapter);

    }

    @Override
    public void onClick(String city) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("city", city);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_cityFragment_to_weatherFragment, bundle);
    }
}