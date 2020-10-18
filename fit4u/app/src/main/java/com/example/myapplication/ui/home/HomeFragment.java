package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ExterneDbHelper;
import com.example.myapplication.R;
import com.example.myapplication.Trainer;
import com.example.myapplication.TrainerAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private HomeViewModel homeViewModel;
    private List<Trainer> trainers;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ExterneDbHelper dbHelper = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");
        try {
            trainers = dbHelper.getNearbyTrainers();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) root.findViewById(R.id.trainerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new TrainerAdapter(trainers);
        recyclerView.setAdapter(mAdapter);


        return root;
    }

    public void buttonClicked(View view) {
        NavController navController = Navigation.findNavController(super.getActivity(), R.id.nav_host_fragment);
    }
}

