package com.example.myapplication;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.ui.home.HomeViewModel;

import org.json.JSONException;

import java.util.List;

public class TrainerProfileFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private HomeViewModel homeViewModel;
    private List<Trainer> trainers;


    private TrainerProfileViewModel mViewModel;

    public static TrainerProfileFragment newInstance() {
        return new TrainerProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_trainer_profile, container, false);

        ExterneDbHelper dbHelper = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");
        try {
            trainers = dbHelper.getFavouriteTrainers(User.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) root.findViewById(R.id.trainerListView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(root.getContext()); // may be wrong
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new TrainerAdapter(trainers);
        recyclerView.setAdapter(mAdapter);


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TrainerProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}