package com.example.myapplication;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


        int index = TrainerProfileFragmentArgs.fromBundle(getArguments()).getIndex();

        ExterneDbHelper dbHelper = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");
        Trainer trainer = null;
        try {
            trainer = dbHelper.getTrainerProfileWithIndex(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("trainer!!: ", trainer.getNaam());

        TextView name = (TextView) root.findViewById(R.id.trainerName);
        name.setText(trainer.getNaam());

        TextView location = (TextView) root.findViewById(R.id.trainerLocation);
        location.setText(trainer.getAdres());

        TextView price = (TextView) root.findViewById(R.id.valuePrice);
        price.setText(Integer.toString(trainer.getUurloon()));



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TrainerProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}