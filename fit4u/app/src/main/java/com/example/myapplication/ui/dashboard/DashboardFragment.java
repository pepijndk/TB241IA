package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ExterneDbHelper;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewClickListener;
import com.example.myapplication.Timeslot;
import com.example.myapplication.TimeslotAdapter;
import com.example.myapplication.Trainer;
import com.example.myapplication.User;
import com.example.myapplication.ui.home.HomeViewModel;

import org.json.JSONException;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private HomeViewModel homeViewModel;
    private List<Timeslot> slots;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ExterneDbHelper dbHelper = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");
        try {
            slots = dbHelper.getUpcomingTrainingsOfSporter(User.getId());
            for(int i = 0; i < slots.size(); i++) {
                Log.d("testing", slots.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerViewClickListener listener = (view, position) -> {
            Toast.makeText(getContext(), "Request sent, no reply received yet.", Toast.LENGTH_SHORT).show();
        };

        // setup recyclerview
        recyclerView = (RecyclerView) root.findViewById(R.id.requestsListView);
        recyclerView.setHasFixedSize(true);

        // setup recyclerview
        layoutManager = new LinearLayoutManager(root.getContext()); // may be wrong
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new TimeslotAdapter(slots, listener);
        recyclerView.setAdapter(mAdapter);




        return root;
    }
}