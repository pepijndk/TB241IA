package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ExterneDbHelper;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewClickListener;
import com.example.myapplication.Trainer;
import com.example.myapplication.TrainerAdapter;
import com.example.myapplication.TrainerProfileFragmentArgs;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    // vars for recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private HomeViewModel homeViewModel;
    private List<Trainer> trainers;

    // function gets called when fragment is laoded
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        // init view
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerViewClickListener listener = (view, position) -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            NavDirections action = HomeFragmentDirections.actionNavigationHomeToNavigationTrainerProfile().setIndex(position);
            navController.navigate(action);
        };


        // setup db helper
        ExterneDbHelper dbHelper = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");
        try {
            // fetch data
            trainers = dbHelper.getNearbyTrainers();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // setup recyclerview
        recyclerView = (RecyclerView) root.findViewById(R.id.trainerListView);
        recyclerView.setHasFixedSize(true);

        // setup recyclerview
        layoutManager = new LinearLayoutManager(root.getContext()); // may be wrong
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new TrainerAdapter(trainers, listener);
        recyclerView.setAdapter(mAdapter);

        return root;
    }

    public void buttonClicked(View view) {
        NavController navController = Navigation.findNavController(super.getActivity(), R.id.nav_host_fragment);
    }
}

