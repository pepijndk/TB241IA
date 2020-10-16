package com.example.myapplication.ui.trainers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrainersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrainersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Trainers fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}