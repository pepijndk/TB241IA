package com.example.myapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.ExterneDbHelper;
import com.example.myapplication.Trainer;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class HomeViewModel extends ViewModel {

//    private MutableLiveData<List<Trainer>> trainers;
//
//
//    public HomeViewModel() {
//
//    }
//
//    public LiveData<List<Trainer>> getTrainers() {
//        if (trainers == null) {
//            trainers = new MutableLiveData<List<Trainer>>();
//            loadTrainers();
//        }
//        return trainers;
//    }
//
//    private void loadTrainers() {
//        ExterneDbHelper dbHelper = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");
//        try {
//            trainers.setValue(dbHelper.getNearbyTrainers());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}