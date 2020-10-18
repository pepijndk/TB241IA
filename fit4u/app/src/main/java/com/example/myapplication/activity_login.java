package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {

    private EditText eEmailadress;
    private EditText ePassword;
    private Button eLogin;

    private String Emailadress = "Admin";
    private String Password = "1234";

    boolean isvalid = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmailadress = findViewById(R.id.etEmailadress);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.button2);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputEmailadress = eEmailadress.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputEmailadress.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(activity_login.this,"Please enter all the details correctly", Toast.LENGTH_SHORT).show();
                }else{

                    isvalid = validate(inputEmailadress, inputPassword);

                    if(!isvalid){

                        Toast.makeText(activity_login.this,"Incorrect credentials", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(activity_login.this,"Login succesful", Toast.LENGTH_SHORT).show();

                        // add code to new acitivity
                        Intent intent = new Intent(activity_login.this, MainActivity.class);
                        startActivity(intent);

                    }

                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private boolean validate(String name, String password){

        if(name.equals(Emailadress) && password.equals(Password)){
            return true;

        }

        return false;

    }
}