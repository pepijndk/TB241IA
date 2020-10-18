package com.example.myapplication.ui.trainers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ExterneDbHelper;
import com.example.myapplication.R;
import com.example.myapplication.Trainer;
import com.example.myapplication.TrainerAdapter;
import com.example.myapplication.ui.home.HomeViewModel;

import org.json.JSONException;

import java.util.List;

public class TrainersFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private HomeViewModel homeViewModel;
    private List<Trainer> trainers;


    private TrainersViewModel trainersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trainersViewModel =
                new ViewModelProvider(this).get(TrainersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trainers, container, false);

        ExterneDbHelper dbHelper = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");
        try {
            trainers = dbHelper.getNearbyTrainers();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        recyclerView = (RecyclerView) root.findViewById(R.id.favouriteTrainerListView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(root.getContext()); // may be wrong
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new TrainerAdapter(trainers);
        recyclerView.setAdapter(mAdapter);

        return root;
    }
}