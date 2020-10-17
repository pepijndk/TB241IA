package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.home.HomeFragmentDirections;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_trainers, R.id.navigation_dashboard, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }




    public void buttonClicked(View view) {
        ExterneDbHelper dbHelper = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");

        // testing
        dbHelper.getUpcomingTrainingsOfSporter(5000);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavDirections action = HomeFragmentDirections.actionNavigationHomeToNavigationTrainerProfile();
//        navController.navigate(action);
    }

}